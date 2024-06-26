package bg.tu_varna.sit.operation.review;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.unverified.GetUnverifiedStudentsRequest;
import bg.tu_varna.sit.model.application.unverified.GetUnverifiedStudentsResponse;
import io.vavr.control.Either;

public interface GetUnverifiedStudentsOperation extends OperationProcessor<GetUnverifiedStudentsRequest, GetUnverifiedStudentsResponse> {
    @Override
    Either<Error, GetUnverifiedStudentsResponse> process(GetUnverifiedStudentsRequest input);
}
