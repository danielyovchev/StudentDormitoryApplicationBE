package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.family.get.GetStudentChildDataRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentChildDataResponse;
import bg.tu_varna.sit.operation.student.family.GetStudentChildDataOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetStudentChildDataService implements GetStudentChildDataOperation {
    @Override
    public Either<Error, GetStudentChildDataResponse> process(GetStudentChildDataRequest input) {
        return null;
    }
}
