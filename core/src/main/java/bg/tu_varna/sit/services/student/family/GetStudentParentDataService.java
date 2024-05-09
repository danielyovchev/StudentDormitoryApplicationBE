package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.family.get.GetStudentParentApplicationRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentParentApplicationResponse;
import bg.tu_varna.sit.operation.student.family.GetStudentParentDataOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetStudentParentDataService implements GetStudentParentDataOperation {
    @Override
    public Either<Error, GetStudentParentApplicationResponse> process(GetStudentParentApplicationRequest input) {
        return null;
    }
}
