package bg.tu_varna.sit.services.student.document;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.documents.UploadDocumentRequest;
import bg.tu_varna.sit.model.application.documents.UploadDocumentResponse;
import bg.tu_varna.sit.operation.student.document.UploadStudentDocumentOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaveStudentDocumentService implements UploadStudentDocumentOperation {
    @Override
    public Either<Error, UploadDocumentResponse> process(UploadDocumentRequest input) {
        return null;
    }
}
