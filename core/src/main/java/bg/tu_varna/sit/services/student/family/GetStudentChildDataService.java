package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Child;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.family.get.GetStudentChildDataRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentChildDataResponse;
import bg.tu_varna.sit.model.dto.ChildDTO;
import bg.tu_varna.sit.operation.student.family.GetStudentChildDataOperation;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentChildDataService implements GetStudentChildDataOperation {
    private final StudentRepository studentRepository;

    @Override
    public Either<Error, GetStudentChildDataResponse> process(GetStudentChildDataRequest input) {
        return Try.of(() -> {
                    Optional<Student> student = studentRepository.findByStudentPersonalNumber(input.getStudentNumber());
                    if (student.isEmpty()) {
                        return GetStudentChildDataResponse.builder()
                                .build();
                    }
                    List<Child> children = student.get().getChildren();
                    List<ChildDTO> childDTOS = children.stream()
                            .map(ch -> ChildDTO.builder()
                                    .name(ch.getName())
                                    .birthDate(ch.getBirthDate())
                                    .build())
                            .toList();
                    return GetStudentChildDataResponse.builder()
                            .children(childDTOS)
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
