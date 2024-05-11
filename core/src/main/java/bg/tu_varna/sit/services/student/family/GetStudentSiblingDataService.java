package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.family.get.GetStudentSiblingDataRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentSiblingDataResponse;
import bg.tu_varna.sit.operation.student.family.GetStudentSiblingDataOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetStudentSiblingDataService implements GetStudentSiblingDataOperation {
    @Override
    public Either<Error, GetStudentSiblingDataResponse> process(GetStudentSiblingDataRequest input) {
        return null;
    }
}
