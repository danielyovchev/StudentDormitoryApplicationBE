package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Spouse;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.family.get.GetStudentSpouseApplicationRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentSpouseApplicationResponse;
import bg.tu_varna.sit.model.dto.SpouseDTO;
import bg.tu_varna.sit.operation.student.family.GetStudentSpouseDataOperation;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentSpouseDataService implements GetStudentSpouseDataOperation {
    private final StudentRepository studentRepository;
    @Override
    public Either<Error, GetStudentSpouseApplicationResponse> process(GetStudentSpouseApplicationRequest input) {
        return Try.of(() -> {
                    Optional<Student> student = studentRepository.findByStudentPersonalNumber(input.getStudentNumber());
                    if (student.isEmpty()) {
                        return GetStudentSpouseApplicationResponse
                                .builder()
                                .build();
                    }
                    Optional<Spouse> spouse = student.get().getSpouses().stream().findFirst();
                    if (spouse.isEmpty()) {
                        return GetStudentSpouseApplicationResponse
                                .builder()
                                .build();
                    }
                    SpouseDTO spouseDTO = SpouseDTO.builder()
                            .name(spouse.get().getName())
                            .city(spouse.get().getCity())
                            .streetNumber(spouse.get().getStreetNumber())
                            .phoneNumber(spouse.get().getPhoneNumber())
                            .street(spouse.get().getStreet())
                            .build();
                    return GetStudentSpouseApplicationResponse.builder()
                            .spouse(spouseDTO)
                            .build();
                }).toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
