package bg.tu_varna.sit.operation.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSpouseApplicationRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSpouseApplicationResponse;
import io.vavr.control.Either;

public interface SaveStudentSpouseDataOperation extends OperationProcessor<SaveStudentSpouseApplicationRequest, SaveStudentSpouseApplicationResponse> {
    @Override
    Either<Error, SaveStudentSpouseApplicationResponse> process(SaveStudentSpouseApplicationRequest input);
}
