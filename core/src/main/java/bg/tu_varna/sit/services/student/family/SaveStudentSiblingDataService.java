package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSiblingDataRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentSiblingDataResponse;
import bg.tu_varna.sit.operation.student.family.SaveStudentSiblingDataOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaveStudentSiblingDataService implements SaveStudentSiblingDataOperation {
    @Override
    public Either<Error, SaveStudentSiblingDataResponse> process(SaveStudentSiblingDataRequest input) {
        return null;
    }
}
