package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.family.save.SaveStudentChildDataRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentChildDataResponse;
import bg.tu_varna.sit.operation.student.family.SaveStudentChildDataOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaveStudentChildDataService implements SaveStudentChildDataOperation {
    @Override
    public Either<Error, SaveStudentChildDataResponse> process(SaveStudentChildDataRequest input) {
        return null;
    }
}
