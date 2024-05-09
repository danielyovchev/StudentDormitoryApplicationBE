package bg.tu_varna.sit.services.student.document;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.documents.GetDocumentRequest;
import bg.tu_varna.sit.model.application.documents.GetDocumentResponse;
import bg.tu_varna.sit.operation.student.GetStudentDocumentOperation;
import io.vavr.control.Either;

public class GetStudentDocumentService implements GetStudentDocumentOperation {
    @Override
    public Either<Error, GetDocumentResponse> process(GetDocumentRequest input) {
        return null;
    }
}
