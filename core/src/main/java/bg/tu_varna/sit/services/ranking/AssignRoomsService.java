package bg.tu_varna.sit.services.ranking;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.RoomOccupancy;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.entity.StudentScore;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.ranking.AssignRoomRequest;
import bg.tu_varna.sit.model.ranking.AssignRoomResponse;
import bg.tu_varna.sit.operation.ranking.AssignRoomsOperation;
import bg.tu_varna.sit.repository.PreferenceRepository;
import bg.tu_varna.sit.repository.RoomOccupancyRepository;
import bg.tu_varna.sit.repository.StudentScoreRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class AssignRoomsService implements AssignRoomsOperation {
    private final StudentScoreRepository studentScoreRepository;
    private final PreferenceRepository preferenceRepository;
    private final RoomOccupancyRepository roomOccupancyRepository;
    @Override
    @Transactional
    public Either<Error, AssignRoomResponse> process(AssignRoomRequest input) {
        return Try.of(() -> {
                    List<Student> students = studentScoreRepository.getOrderedStudentScores().stream()
                            .map(StudentScore::getStudent)
                            .toList();
                    List<RoomOccupancy>
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
