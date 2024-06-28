package bg.tu_varna.sit.operation.application;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.GetApplicationRequest;
import bg.tu_varna.sit.model.application.GetApplicationResponse;
import io.vavr.control.Either;

public interface GetStudentApplicationOperation extends OperationProcessor<GetApplicationRequest, GetApplicationResponse> {
    @Override
    Either<Error, GetApplicationResponse> process(GetApplicationRequest input);
}
