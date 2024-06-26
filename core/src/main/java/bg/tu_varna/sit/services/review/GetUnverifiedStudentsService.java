package bg.tu_varna.sit.services.review;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Application;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.unverified.GetUnverifiedStudentsRequest;
import bg.tu_varna.sit.model.application.unverified.GetUnverifiedStudentsResponse;
import bg.tu_varna.sit.model.dto.ApplicationDTO;
import bg.tu_varna.sit.operation.review.GetUnverifiedStudentsOperation;
import bg.tu_varna.sit.repository.ApplicationRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class GetUnverifiedStudentsService implements GetUnverifiedStudentsOperation {
    private final ApplicationRepository applicationRepository;

    @Override
    public Either<Error, GetUnverifiedStudentsResponse> process(GetUnverifiedStudentsRequest input) {
        return Try.of(() -> {
                    List<Application> unverifiedApplications = applicationRepository.findByUnverified();
                    List<ApplicationDTO> applications = unverifiedApplications.stream()
                            .map(app -> ApplicationDTO.builder()
                                    .studentName(app.getStudent().getName())
                                    .applicationDate(app.getApplicationDate())
                                    .status(app.getStatus().toString())
                                    .build())
                            .toList();
                    return GetUnverifiedStudentsResponse.builder()
                            .applications(applications)
                            .build();
                }).toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
