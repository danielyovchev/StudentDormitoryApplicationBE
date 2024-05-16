package bg.tu_varna.sit.services.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.SaveStudentApplicationResponse;
import bg.tu_varna.sit.operation.student.SaveStudentApplicationDataOperation;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SaveStudentApplicationDataService implements SaveStudentApplicationDataOperation {
    @Transactional
    @Override
    public Either<Error, SaveStudentApplicationResponse> process(SaveStudentApplicationRequest input) {
        return Try.of(() -> {
                    Student student = new Student();
                    student.setName(input.getName());
                    student.setApartment(input.getApartment());
                    student.setCity(input.getCity());
                    student.setEntrance(input.getEntrance());
                    student.setMunicipality(input.getMunicipality());
                    student.setStreetNumber(input.getStreetNumber());
                    student.setStreet(input.getStreet());
                    student.setPersonalNumber(input.getPersonalNumber());
                    student.setPhoneNumber(input.getPhoneNumber());
                    student.persist();
                    return SaveStudentApplicationResponse.builder()
                            .message("Success")
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
