package bg.tu_varna.sit.operation.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.documents.GetDocumentRequest;
import bg.tu_varna.sit.model.application.documents.GetDocumentResponse;
import io.vavr.control.Either;

public interface GetStudentDocumentOperation extends OperationProcessor<GetDocumentRequest, GetDocumentResponse> {
    @Override
    Either<Error, GetDocumentResponse> process(GetDocumentRequest input);
}
