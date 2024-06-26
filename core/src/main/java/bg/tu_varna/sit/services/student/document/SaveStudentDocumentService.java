package bg.tu_varna.sit.services.student.document;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.error.StudentNotFoundError;
import bg.tu_varna.sit.exception.StudentNotFoundException;
import bg.tu_varna.sit.model.application.documents.UploadDocumentRequest;
import bg.tu_varna.sit.model.application.documents.UploadDocumentResponse;
import bg.tu_varna.sit.operation.student.document.UploadStudentDocumentOperation;
import bg.tu_varna.sit.repository.DocumentRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class SaveStudentDocumentService implements UploadStudentDocumentOperation {
    private final DocumentRepository documentRepository;
    private final StudentRepository studentRepository;
    @Transactional
    @Override
    public Either<Error, UploadDocumentResponse> process(UploadDocumentRequest input) {
        return Try.of(() -> {
                    Optional<Student> student = studentRepository.findByStudentPersonalNumber(input.getStudentNumber());
                    if (student.isEmpty()) {
                        throw new StudentNotFoundException();
                    }
                    Document document = new Document();
                    File file = input.getFileUpload().uploadedFile().toFile();
                    byte[] byteArray = Files.readAllBytes(file.toPath());
                    document.setFile(byteArray);
                    document.setFileName(file.getName());
                    document.setValidated(Boolean.FALSE);
                    document.setStudent(student.get());
                    documentRepository.persist(document);
                    return UploadDocumentResponse.builder()
                            .message("Document uploaded successfully")
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
