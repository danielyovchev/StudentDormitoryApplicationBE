package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.get.GetStudentSpouseApplicationRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentSpouseApplicationResponse;
import io.vavr.control.Either;

public interface GetStudentSpouseDataOperation extends OperationProcessor<GetStudentSpouseApplicationRequest, GetStudentSpouseApplicationResponse> {
    @Override
    Either<Error, GetStudentSpouseApplicationResponse> process(GetStudentSpouseApplicationRequest input);
}
