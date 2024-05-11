package bg.tu_varna.sit.operation.student.document;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.documents.UploadDocumentRequest;
import bg.tu_varna.sit.model.application.documents.UploadDocumentResponse;
import io.vavr.control.Either;

public interface UploadStudentDocumentOperation extends OperationProcessor<UploadDocumentRequest, UploadDocumentResponse> {
    @Override
    Either<Error, UploadDocumentResponse> process(UploadDocumentRequest input);
}
