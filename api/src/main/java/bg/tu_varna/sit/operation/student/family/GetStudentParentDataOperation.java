package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.GetStudentParentApplicationRequest;
import bg.tu_varna.sit.model.application.family.GetStudentParentApplicationResponse;
import io.vavr.control.Either;

public interface GetStudentParentDataOperation extends OperationProcessor<GetStudentParentApplicationRequest, GetStudentParentApplicationResponse> {
    @Override
    Either<Error, GetStudentParentApplicationResponse> process(GetStudentParentApplicationRequest input);
}
