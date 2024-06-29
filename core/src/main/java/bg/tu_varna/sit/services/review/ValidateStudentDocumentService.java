package bg.tu_varna.sit.services.review;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.error.DocumentNotFoundError;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.exception.DocumentNotFoundException;
import bg.tu_varna.sit.model.application.documents.ValidateDocumentRequest;
import bg.tu_varna.sit.model.application.documents.ValidateDocumentResponse;
import bg.tu_varna.sit.operation.review.ValidateStudentDocumentOperation;
import bg.tu_varna.sit.repository.DocumentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class ValidateStudentDocumentService implements ValidateStudentDocumentOperation {
    private final DocumentRepository documentRepository;
    @Transactional
    @Override
    public Either<Error, ValidateDocumentResponse> process(ValidateDocumentRequest input) {
        return Try.of(() -> {
            Optional<Document> document = Optional.ofNullable(documentRepository.findById(input.getDocumentId()));
            if (document.isEmpty()) {
                throw new DocumentNotFoundException();
            }
            Document documentEntity = document.get();
            documentEntity.setValidated(true);
            documentRepository.persist(documentEntity);
            return ValidateDocumentResponse.builder().build();
                }).toEither()
                .mapLeft(Throwable -> {
                    if (Throwable instanceof DocumentNotFoundException) {
                        return new DocumentNotFoundError();
                    }
                    return new InternalError();
                });
    }
}
