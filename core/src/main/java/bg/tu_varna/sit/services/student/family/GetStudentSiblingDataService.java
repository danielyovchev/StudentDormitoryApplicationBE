package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.family.get.GetStudentSiblingDataRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentSiblingDataResponse;
import bg.tu_varna.sit.operation.student.family.GetStudentSiblingDataOperation;
import bg.tu_varna.sit.repository.SiblingRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentSiblingDataService implements GetStudentSiblingDataOperation {
    private final SiblingRepository siblingRepository;

    @Override
    public Either<Error, GetStudentSiblingDataResponse> process(GetStudentSiblingDataRequest input) {
        return Try.of(() -> {
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
