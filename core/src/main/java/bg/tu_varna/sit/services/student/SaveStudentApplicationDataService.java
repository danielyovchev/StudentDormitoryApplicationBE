package bg.tu_varna.sit.services.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.SaveStudentApplicationDataOperation;
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
    @Transactional
    @Override
    public Either<Error, SaveStudentApplicationResponse> process(SaveStudentApplicationRequest input) {
        return Try.of(() -> {
                    Optional<Student> studentOptional = studentRepository.findByStudentPersonalNumber(input.getPersonalNumber());
                    if (studentOptional.isPresent()) {
                        Student student = studentOptional.get();
                        updateExistingStudentData(input, student);
                        studentRepository.persist(student);
                        return SaveStudentApplicationResponse.builder()
                                .message("Student data updated")
                                .build();
                    }
                    Student student = writeStudentData(input);
                    studentRepository.persist(student);
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
