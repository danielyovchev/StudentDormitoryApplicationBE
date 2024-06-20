package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Spouse;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.error.StudentNotFoundError;
import bg.tu_varna.sit.exception.StudentNotFoundException;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSpouseApplicationRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSpouseApplicationResponse;
import bg.tu_varna.sit.operation.student.family.SaveStudentSpouseDataOperation;
import bg.tu_varna.sit.repository.SpouseRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class SaveStudentSpouseDataService implements SaveStudentSpouseDataOperation {
    private final StudentRepository studentRepository;
    private final SpouseRepository spouseRepository;
    @Override
    public Either<Error, SaveStudentSpouseApplicationResponse> process(SaveStudentSpouseApplicationRequest input) {
        return Try.of(() -> {
                    Student student = Option.ofOptional(studentRepository.findByStudentPersonalNumber(input.getStudentPersonalNumber()))
                            .getOrElseThrow(StudentNotFoundException::new);
                    Spouse spouse = new Spouse();
                    spouse.setName(input.getName());
                    spouse.setCity(input.getCity());
                    spouse.setStreet(input.getStreet());
                    spouse.setPhoneNumber(input.getPhoneNumber());
                    spouse.setStreetNumber(input.getStreetNumber());
                    spouse.setStudent(student);
                    spouseRepository.persist(spouse);
                    return SaveStudentSpouseApplicationResponse.builder()
                            .message("Data for spouse saved")
                            .build();
                }).toEither()
                .mapLeft(Throwable -> {
                    if (Throwable instanceof StudentNotFoundException) {
                        return new StudentNotFoundError();
                    }
                    return new InternalError();
                });
    }
}
