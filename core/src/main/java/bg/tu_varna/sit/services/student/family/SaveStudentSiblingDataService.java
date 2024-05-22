package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Sibling;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.error.StudentNotFoundError;
import bg.tu_varna.sit.exception.StudentNotFoundException;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSiblingDataRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSiblingDataResponse;
import bg.tu_varna.sit.operation.student.family.SaveStudentSiblingDataOperation;
import bg.tu_varna.sit.repository.SiblingRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class SaveStudentSiblingDataService implements SaveStudentSiblingDataOperation {
    private final SiblingRepository siblingRepository;
    private final StudentRepository studentRepository;
    @Transactional
    @Override
    public Either<Error, SaveStudentSiblingDataResponse> process(SaveStudentSiblingDataRequest input) {
        return Try.of(() -> {
                    Student student = Option.ofOptional(studentRepository.findByStudentPersonalNumber(input.getStudentPersonalNumber()))
                            .getOrElseThrow(StudentNotFoundException::new);
                    Sibling sibling = new Sibling();
                    sibling.setName(input.getName());
                    sibling.setIsStudying(input.getIsStudying());
                    sibling.setStudent(student);
                    siblingRepository.persist(sibling);
                    return SaveStudentSiblingDataResponse.builder()
                            .message("Good")
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
