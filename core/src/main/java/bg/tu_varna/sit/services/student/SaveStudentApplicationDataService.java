package bg.tu_varna.sit.services.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Dormitory;
import bg.tu_varna.sit.entity.Preference;
import bg.tu_varna.sit.entity.Room;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.SaveStudentApplicationDataOperation;
import bg.tu_varna.sit.repository.DormitoryRepository;
import bg.tu_varna.sit.repository.PreferenceRepository;
import bg.tu_varna.sit.repository.RoomRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class SaveStudentApplicationDataService implements SaveStudentApplicationDataOperation {
    private final StudentRepository studentRepository;
    private final PreferenceRepository preferenceRepository;
    private final RoomRepository roomRepository;
    private final DormitoryRepository dormitoryRepository;
    @Transactional
    @Override
    public Either<Error, SaveStudentApplicationResponse> process(SaveStudentApplicationRequest input) {
        return Try.of(() -> {
                    Optional<Student> studentOptional = studentRepository.findByStudentPersonalNumber(input.getStudentNumber());
                    if (studentOptional.isPresent()) {
                        Student student = studentOptional.get();
                        updateExistingStudentData(input, student);
                        Optional<Preference> preference = preferenceRepository.findStudentPreference(student.getId());
                        Dormitory dormitory = dormitoryRepository.findByName(input.getDormitoryNumber().toString());
                        Room room = roomRepository.findByRoomNumberAndDormitory(String.valueOf(input.getRoomNumber()), dormitory.getId());
                        if (preference.isPresent()) {
                            Preference pref = preference.get();
                            pref.setDormitory(dormitory);
                            pref.setPreferredRoom(room);
                            preferenceRepository.persist(preference.get());
                            studentRepository.persist(student);
                            return SaveStudentApplicationResponse.builder()
                                    .message("Student data updated")
                                    .build();
                        }
                        Preference preferenceToSave = new Preference();
                        preferenceToSave.setStudent(student);
                        preferenceToSave.setDormitory(dormitory);
                        preferenceToSave.setPreferredRoom(room);
                        studentRepository.persist(student);
                        preferenceRepository.persist(preferenceToSave);
                        return SaveStudentApplicationResponse.builder()
                                .message("Student data updated")
                                .build();
                    }
                    Student student = writeStudentData(input);

                    Dormitory dormitory = dormitoryRepository.findByName(input.getDormitoryNumber().toString());
                    Room room = roomRepository.findByRoomNumberAndDormitory(String.valueOf(input.getRoomNumber()), dormitory.getId());

                    Preference preferenceToSave = new Preference();
                    preferenceToSave.setStudent(student);
                    preferenceToSave.setDormitory(dormitory);
                    preferenceToSave.setPreferredRoom(room);
                    studentRepository.persist(student);
                    preferenceRepository.persist(preferenceToSave);
                    return SaveStudentApplicationResponse.builder()
                            .message("Student data saved")
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }

    private void updateExistingStudentData(SaveStudentApplicationRequest input, Student student) {
        student.setName(input.getName());
        student.setApartment(input.getApartment());
        student.setCity(input.getCity());
        student.setGrade(input.getGrade());
        student.setEntrance(input.getEntrance());
        student.setMunicipality(input.getMunicipality());
        student.setStreetNumber(input.getStreetNumber());
        student.setStreet(input.getStreet());
        student.setPersonalNumber(input.getPersonalNumber());
        student.setPhoneNumber(input.getPhoneNumber());
        student.setSex(input.getSex());
        student.setStudentNumber(input.getStudentNumber());
        student.setSpeciality(input.getSpecialty().toString());
        student.setFaculty(input.getFaculty().toString());
    }

    private Student writeStudentData(SaveStudentApplicationRequest input) {
        Student student = new Student();
        updateExistingStudentData(input, student);
        return student;
    }
}
