package bg.tu_varna.sit.services.application;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Application;
import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.enums.ApplicationStatus;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.error.StudentNotFoundError;
import bg.tu_varna.sit.exception.StudentNotFoundException;
import bg.tu_varna.sit.model.application.SaveApplicationRequest;
import bg.tu_varna.sit.model.application.SaveApplicationResponse;
import bg.tu_varna.sit.operation.application.SaveStudentApplicationOperation;
import bg.tu_varna.sit.repository.ApplicationRepository;
import bg.tu_varna.sit.repository.DocumentRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class SaveStudentApplicationService implements SaveStudentApplicationOperation {
    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final DocumentRepository documentRepository;
    @Override
    @Transactional
    public Either<Error, SaveApplicationResponse> process(SaveApplicationRequest input) {
        return Try.of(() -> {
                    Optional<Student> student = studentRepository.findByStudentPersonalNumber(input.getStudentNumber());
                    if (student.isEmpty()) {
                        throw new StudentNotFoundException();
                    }
                    List<Document> documents = documentRepository.getStudentUnverifiedDocuments(student.get().getId());
                    Application application = new Application();
                    application.setStudent(student.get());
                    if(documents.isEmpty()) {
                        application.setStatus(ApplicationStatus.SUCCESSFUL);
                    }
                    else {
                        application.setStatus(ApplicationStatus.PENDING);
                    }
                    applicationRepository.persist(application);
                    return SaveApplicationResponse.builder()
                            .message("Application successful.")
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
