package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.GetStudentFamilyApplicationRequest;
import bg.tu_varna.sit.model.application.family.GetStudentFamilyApplicationResponse;
import io.vavr.control.Either;

public interface GetStudentFamilyDataOperation extends OperationProcessor<GetStudentFamilyApplicationRequest, GetStudentFamilyApplicationResponse> {
    @Override
    Either<Error, GetStudentFamilyApplicationResponse> process(GetStudentFamilyApplicationRequest input);
}
