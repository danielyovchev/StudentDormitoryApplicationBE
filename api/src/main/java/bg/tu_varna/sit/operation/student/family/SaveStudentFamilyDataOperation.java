package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.SaveStudentFamilyApplicationRequest;
import bg.tu_varna.sit.model.application.family.SaveStudentFamilyApplicationResponse;
import io.vavr.control.Either;

public interface SaveStudentFamilyDataOperation extends OperationProcessor<SaveStudentFamilyApplicationRequest, SaveStudentFamilyApplicationResponse> {
    @Override
    Either<Error, SaveStudentFamilyApplicationResponse> process(SaveStudentFamilyApplicationRequest input);
}
