package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Sibling;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.family.get.GetStudentSiblingDataRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentSiblingDataResponse;
import bg.tu_varna.sit.model.dto.SiblingDTO;
import bg.tu_varna.sit.operation.student.family.GetStudentSiblingDataOperation;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentSiblingDataService implements GetStudentSiblingDataOperation {
    private final StudentRepository studentRepository;

    @Override
    public Either<Error, GetStudentSiblingDataResponse> process(GetStudentSiblingDataRequest input) {
        return Try.of(() -> {
                    Optional<Student> student = studentRepository.findByStudentPersonalNumber(input.getStudentNumber());
                    if (student.isEmpty()) {
                        return GetStudentSiblingDataResponse.builder()
                                .build();
                    }
                    List<Sibling> siblings = student.get().getSiblings();
                    List<SiblingDTO> siblingDTOS = siblings.stream()
                            .map(sibling -> SiblingDTO.builder()
                                    .name(sibling.getName())
                                    .isStudying(sibling.getIsStudying())
                                    .build())
                            .toList();
                    return GetStudentSiblingDataResponse.builder()
                            .siblings(siblingDTOS)
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
