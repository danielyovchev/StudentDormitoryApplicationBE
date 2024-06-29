package bg.tu_varna.sit.operation.review;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.documents.ValidateDocumentRequest;
import bg.tu_varna.sit.model.application.documents.ValidateDocumentResponse;
import io.vavr.control.Either;

public interface ValidateStudentDocumentOperation extends OperationProcessor<ValidateDocumentRequest, ValidateDocumentResponse> {
    @Override
    Either<Error, ValidateDocumentResponse> process(ValidateDocumentRequest input);
}
