package bg.tu_varna.sit.services.student.document;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.enums.DocumentEnum;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.error.StudentNotFoundError;
import bg.tu_varna.sit.exception.StudentNotFoundException;
import bg.tu_varna.sit.model.application.documents.UploadDocumentRequest;
import bg.tu_varna.sit.model.application.documents.UploadDocumentResponse;
import bg.tu_varna.sit.operation.student.document.UploadStudentDocumentOperation;
import bg.tu_varna.sit.repository.DocumentRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import bg.tu_varna.sit.service.AzureBlobService;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class SaveStudentDocumentService implements UploadStudentDocumentOperation {
    private final DocumentRepository documentRepository;
    private final StudentRepository studentRepository;
    private final AzureBlobService azureBlobService;

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
                    String contentType = input.getFileUpload().contentType();
                    try (InputStream inputStream = new FileInputStream(file)) {
                        String blobUrl = azureBlobService.uploadFile(inputStream, file.getName(), contentType);
                        document.setFileUrl(blobUrl);
                        document.setFileName(file.getName());
                        document.setValidated(Boolean.FALSE);
                        document.setDocumentEnum(DocumentEnum.valueOf(input.getDocumentType().toString()));
                        document.setStudent(student.get());
                        documentRepository.persist(document);
                    } finally {
                        file.deleteOnExit();
                    }

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
