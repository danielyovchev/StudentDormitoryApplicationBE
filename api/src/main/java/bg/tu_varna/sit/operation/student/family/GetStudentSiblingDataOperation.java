package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.get.GetStudentSiblingDataRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentSiblingDataResponse;
import io.vavr.control.Either;

public interface GetStudentSiblingDataOperation extends OperationProcessor<GetStudentSiblingDataRequest, GetStudentSiblingDataResponse> {
    @Override
    Either<Error, GetStudentSiblingDataResponse> process(GetStudentSiblingDataRequest input);
}
