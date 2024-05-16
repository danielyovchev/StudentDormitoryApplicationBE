package bg.tu_varna.sit.services.student.document;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.documents.UploadDocumentRequest;
import bg.tu_varna.sit.model.application.documents.UploadDocumentResponse;
import bg.tu_varna.sit.operation.student.document.UploadStudentDocumentOperation;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.File;
import java.nio.file.Files;

@ApplicationScoped
public class SaveStudentDocumentService implements UploadStudentDocumentOperation {
    @Override
    public Either<Error, UploadDocumentResponse> process(UploadDocumentRequest input) {
        return Try.of(() -> {
                    Document document = new Document();
                    File file = input.getFileUpload().uploadedFile().toFile();
                    byte[] byteArray = Files.readAllBytes(file.toPath());
                    document.setFile(byteArray);
                    document.persist();
                    return UploadDocumentResponse.builder()
                            .message("Fk")
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> {
                    return new InternalError();
                });
    }
}
