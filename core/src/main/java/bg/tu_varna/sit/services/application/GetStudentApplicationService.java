package bg.tu_varna.sit.services.application;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Application;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.GetApplicationRequest;
import bg.tu_varna.sit.model.application.GetApplicationResponse;
import bg.tu_varna.sit.model.dto.ApplicationDTO;
import bg.tu_varna.sit.operation.application.GetStudentApplicationOperation;
import bg.tu_varna.sit.repository.ApplicationRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentApplicationService implements GetStudentApplicationOperation {
    private final ApplicationRepository applicationRepository;

    @Override
    public Either<Error, GetApplicationResponse> process(GetApplicationRequest input) {
        return Try.of(() -> {
                    Optional<Application> application = applicationRepository.findByStudentNumber(input.getStudentNumber());
                    if (application.isPresent()) {
                        Application applicationEntity = application.get();
                        return GetApplicationResponse.builder()
                                .applicationDTO(ApplicationDTO.builder()
                                        .studentName(applicationEntity.getStudent().getName())
                                        .status(applicationEntity.getStatus().toString())
                                        .applicationDate(applicationEntity.getApplicationDate())
                                        .build())
                                .build();
                    }
                    return GetApplicationResponse.builder().build();
                }).toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
