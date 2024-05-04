package bg.tu_varna.sit.operation.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationResponse;
import io.vavr.control.Either;

public interface GetStudentApplicationDataOperation extends OperationProcessor<GetStudentApplicationRequest, GetStudentApplicationResponse> {
    @Override
    Either<Error, GetStudentApplicationResponse> process(GetStudentApplicationRequest input);
}
