package bg.tu_varna.sit.operation.application;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.SaveApplicationRequest;
import bg.tu_varna.sit.model.application.SaveApplicationResponse;
import io.vavr.control.Either;

public interface SaveStudentApplicationOperation extends OperationProcessor<SaveApplicationRequest, SaveApplicationResponse> {
    @Override
    Either<Error, SaveApplicationResponse> process(SaveApplicationRequest input);
}
