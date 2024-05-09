package bg.tu_varna.sit.services.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.GetStudentApplicationDataOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetStudentApplicationDataService implements GetStudentApplicationDataOperation {
    @Override
    public Either<Error, GetStudentApplicationResponse> process(GetStudentApplicationRequest input) {
        return null;
    }
}
