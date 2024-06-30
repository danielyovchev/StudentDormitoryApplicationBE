package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Parent;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.error.StudentNotFoundError;
import bg.tu_varna.sit.exception.StudentNotFoundException;
import bg.tu_varna.sit.model.application.family.save.SaveStudentParentApplicationRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentParentApplicationResponse;
import bg.tu_varna.sit.operation.student.family.SaveStudentParentDataOperation;
import bg.tu_varna.sit.repository.ParentRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class SaveStudentParentDataService implements SaveStudentParentDataOperation {
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    @Transactional
    @Override
    public Either<Error, SaveStudentParentApplicationResponse> process(SaveStudentParentApplicationRequest input) {
        return Try.of(() -> {
                    Student student = Option.ofOptional(studentRepository.findByStudentPersonalNumber(input.getStudentNumber()))
                            .getOrElseThrow(StudentNotFoundException::new);
                    Parent parent = new Parent();
                    parent.setName(input.getName());
                    parent.setCity(input.getCity());
                    parent.setAddress(input.getAddress());
                    parent.setPhoneNumber(input.getPhoneNumber());
                    parent.setParentType(input.getParentType().getLabel());
                    parent.setStudent(student);
                    parentRepository.persist(parent);
                    return SaveStudentParentApplicationResponse.builder()
                            .message("Data for parent saved")
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> {
                    if (Throwable instanceof StudentNotFoundException) {
                        return new StudentNotFoundError();
                    }
                    return new InternalError();
                });
    }
}
