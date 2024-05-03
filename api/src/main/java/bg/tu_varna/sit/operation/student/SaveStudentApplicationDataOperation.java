package bg.tu_varna.sit.operation.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationResponse;
import io.vavr.control.Either;

public interface SaveStudentApplicationDataOperation extends OperationProcessor<SaveStudentApplicationRequest, SaveStudentApplicationResponse> {
    @Override
    Either<Error, SaveStudentApplicationResponse> process(SaveStudentApplicationRequest input);
}
