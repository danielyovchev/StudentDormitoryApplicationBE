package bg.tu_varna.sit.services;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.SaveStudentApplicationDataOperation;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaveStudentApplicationDataService implements SaveStudentApplicationDataOperation {
    @Override
    public Either<Error, SaveStudentApplicationResponse> process(SaveStudentApplicationRequest input) {
        return null;
    }
}
