package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.get.GetStudentChildDataRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentChildDataResponse;
import io.vavr.control.Either;

public interface GetStudentChildDataOperation extends OperationProcessor<GetStudentChildDataRequest, GetStudentChildDataResponse> {
    @Override
    Either<Error, GetStudentChildDataResponse> process(GetStudentChildDataRequest input);
}
