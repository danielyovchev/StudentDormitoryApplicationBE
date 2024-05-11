package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.save.SaveStudentChildDataRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentChildDataResponse;
import io.vavr.control.Either;

public interface SaveStudentChildDataOperation extends OperationProcessor<SaveStudentChildDataRequest, SaveStudentChildDataResponse> {
    @Override
    Either<Error, SaveStudentChildDataResponse> process(SaveStudentChildDataRequest input);
}
