package bg.tu_varna.sit.services.review;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.enums.ApplicationStatus;
import bg.tu_varna.sit.error.DocumentNotFoundError;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.exception.DocumentNotFoundException;
import bg.tu_varna.sit.model.application.documents.ValidateDocumentRequest;
import bg.tu_varna.sit.model.application.documents.ValidateDocumentResponse;
import bg.tu_varna.sit.operation.review.ValidateStudentDocumentOperation;
import bg.tu_varna.sit.repository.ApplicationRepository;
import bg.tu_varna.sit.repository.DocumentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class ValidateStudentDocumentService implements ValidateStudentDocumentOperation {
    private final DocumentRepository documentRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    @Override
    public Either<Error, ValidateDocumentResponse> process(ValidateDocumentRequest input) {
        return Try.of(() -> {
                    Document document = documentRepository.findByIdOptional(input.getDocumentId())
                            .orElseThrow(DocumentNotFoundException::new);
                    UUID studentId = document.getStudent().getId();

                    validateDocument(document);
                    if (areAllDocumentsVerified(studentId)) {
                        markApplicationAsSuccessful(studentId);
                    }

                    return new ValidateDocumentResponse("Document validated");
                }).toEither()
                .mapLeft(this::mapToError);
    }

    private void validateDocument(Document document) {
        document.setValidated(true);
        documentRepository.persist(document);
    }

    private boolean areAllDocumentsVerified(UUID studentId) {
        return documentRepository.getStudentUnverifiedDocuments(studentId).isEmpty();
    }

    private void markApplicationAsSuccessful(UUID studentId) {
        applicationRepository.findByStudentId(studentId).ifPresent(application -> {
            if (application.getStatus() == ApplicationStatus.PENDING) {
                application.setStatus(ApplicationStatus.SUCCESSFUL);
                applicationRepository.persist(application);
            }
        });
    }

    private Error mapToError(Throwable throwable) {
        if (throwable instanceof DocumentNotFoundException) {
            return new DocumentNotFoundError();
        }
        return new InternalError();
    }

}
