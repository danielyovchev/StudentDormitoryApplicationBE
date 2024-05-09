package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.family.save.SaveStudentParentApplicationRequest;
import bg.tu_varna.sit.model.application.family.save.SaveStudentParentApplicationResponse;
import bg.tu_varna.sit.operation.student.family.SaveStudentParentDataOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaveStudentParentDataService implements SaveStudentParentDataOperation {
    @Override
    public Either<Error, SaveStudentParentApplicationResponse> process(SaveStudentParentApplicationRequest input) {
        return null;
    }
}
