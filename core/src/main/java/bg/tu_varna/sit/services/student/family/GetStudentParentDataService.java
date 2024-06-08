package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.family.get.GetStudentParentApplicationRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentParentApplicationResponse;
import bg.tu_varna.sit.operation.student.family.GetStudentParentDataOperation;
import bg.tu_varna.sit.repository.ParentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentParentDataService implements GetStudentParentDataOperation {
    private final ParentRepository parentRepository;
    @Override
    public Either<Error, GetStudentParentApplicationResponse> process(GetStudentParentApplicationRequest input) {
        return Try.of(() -> {})
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
