package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Child;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.error.StudentNotFoundError;
import bg.tu_varna.sit.exception.StudentNotFoundException;
import bg.tu_varna.sit.model.application.family.save.SaveStudentChildDataRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentChildDataResponse;
import bg.tu_varna.sit.operation.student.family.SaveStudentChildDataOperation;
import bg.tu_varna.sit.repository.ChildRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class SaveStudentChildDataService implements SaveStudentChildDataOperation {
    private final ChildRepository childRepository;
    private final StudentRepository studentRepository;

    @Transactional
    @Override
    public Either<Error, SaveStudentChildDataResponse> process(SaveStudentChildDataRequest input) {
        return Try.of(() -> {
                    Student student = Option.ofOptional(studentRepository.findByStudentPersonalNumber(input.getStudentPersonalNumber()))
                            .getOrElseThrow(StudentNotFoundException::new);
                    Child child = new Child();
                    child.setName(input.getName());
                    child.setBirthDate(input.getBirthDate());
                    child.setStudent(student);
                    childRepository.persist(child);
                    return SaveStudentChildDataResponse.builder()
                            .message("Great success")
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
