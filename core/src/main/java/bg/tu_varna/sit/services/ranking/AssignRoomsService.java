package bg.tu_varna.sit.services.ranking;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.*;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.ranking.AssignRoomRequest;
import bg.tu_varna.sit.model.ranking.AssignRoomResponse;
import bg.tu_varna.sit.operation.ranking.AssignRoomsOperation;
import bg.tu_varna.sit.repository.PreferenceRepository;
import bg.tu_varna.sit.repository.RoomOccupancyRepository;
import bg.tu_varna.sit.repository.RoomRepository;
import bg.tu_varna.sit.repository.StudentScoreRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class AssignRoomsService implements AssignRoomsOperation {
    private final StudentScoreRepository studentScoreRepository;
    private final PreferenceRepository preferenceRepository;
    private final RoomOccupancyRepository roomOccupancyRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public Either<Error, AssignRoomResponse> process(AssignRoomRequest input) {
        return Try.of(() -> {
                    List<Student> students = studentScoreRepository.getOrderedStudentScores().stream()
                            .map(StudentScore::getStudent)
                            .toList();
                    List<Preference> preferences = preferenceRepository.listAll();
                    List<RoomOccupancy> currentRoomOccupancies = roomOccupancyRepository.listAll();

                    // Create maps for quick lookups
                    Map<UUID, Preference> studentPreferences = preferences.stream()
                            .collect(Collectors.toMap(p -> p.getStudent().getId(), preference -> preference));

                    Map<UUID, RoomOccupancy> currentOccupancies = currentRoomOccupancies.stream()
                            .collect(Collectors.toMap(r -> r.getStudent().getId(), occupancy -> occupancy));

                    // First assign rooms to students who want the same room from last year
                    List<Student> remainingStudents = assignPreferredRooms(students, studentPreferences, currentOccupancies);

                    // Assign remaining students based on their scores
                    assignRoomsBasedOnScores(remainingStudents);
                    return AssignRoomResponse.builder()
                            .message("Rooms assigned.")
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }

    private List<Student> assignPreferredRooms(List<Student> students, Map<UUID, Preference> studentPreferences, Map<UUID, RoomOccupancy> currentOccupancies) {
        List<Student> remainingStudents = new ArrayList<>();

        for (Student student : students) {
            Preference preference = studentPreferences.get(student.getId());

            if (preference != null && preference.getPreferredRoom() != null) {
                RoomOccupancy roomOccupancy = currentOccupancies.get(student.getId());
                if (roomOccupancy != null && roomOccupancy.getRoom().getId().equals(preference.getPreferredRoom().getId())) {
                    // Preserve the same room
                    roomOccupancyRepository.persist(roomOccupancy);
                    currentOccupancies.remove(student.getId());
                } else {
                    remainingStudents.add(student);
                }
            } else {
                remainingStudents.add(student);
            }
        }

        return remainingStudents;
    }

    private void assignRoomsBasedOnScores(List<Student> students) {
        List<Room> availableRooms = roomRepository.listAll();
        Map<Long, List<RoomOccupancy>> roomOccupancies = new HashMap<>();

        for (Room room : availableRooms) {
            roomOccupancies.put(room.getId(), roomOccupancyRepository.find("room.id", room.getId()).list());
        }

        for (Student student : students) {
            Room assignedRoom = findAvailableRoom(roomOccupancies);

            if (assignedRoom != null) {
                RoomOccupancy roomOccupancy = new RoomOccupancy();
                roomOccupancy.setRoom(assignedRoom);
                roomOccupancy.setStudent(student);
                roomOccupancy.setStartDate(LocalDate.now());
                roomOccupancyRepository.persist(roomOccupancy);

                roomOccupancies.get(assignedRoom.getId()).add(roomOccupancy);
            } else {
                throw new RuntimeException("No available rooms for student: " + student.getId());
            }
        }
    }

    private Room findAvailableRoom(Map<Long, List<RoomOccupancy>> roomOccupancies) {
        for (Map.Entry<Long, List<RoomOccupancy>> entry : roomOccupancies.entrySet()) {
            Room room = roomRepository.findById(entry.getKey());
            List<RoomOccupancy> occupancies = entry.getValue();
            if (occupancies.size() < room.getCapacity()) {
                return room;
            }
        }
        return null;
    }
}
