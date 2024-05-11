package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSiblingDataRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSiblingDataResponse;
import io.vavr.control.Either;

public interface SaveStudentSiblingDataOperation extends OperationProcessor<SaveStudentSiblingDataRequest, SaveStudentSiblingDataResponse> {
    @Override
    Either<Error, SaveStudentSiblingDataResponse> process(SaveStudentSiblingDataRequest input);
}
