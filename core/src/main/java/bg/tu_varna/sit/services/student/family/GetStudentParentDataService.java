package bg.tu_varna.sit.services.student.family;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Parent;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.family.get.GetStudentParentApplicationRequest;
import bg.tu_varna.sit.model.application.family.get.GetStudentParentApplicationResponse;
import bg.tu_varna.sit.model.dto.ParentDTO;
import bg.tu_varna.sit.model.enums.ParentType;
import bg.tu_varna.sit.operation.student.family.GetStudentParentDataOperation;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentParentDataService implements GetStudentParentDataOperation {
    private final StudentRepository studentRepository;

    @Override
    public Either<Error, GetStudentParentApplicationResponse> process(GetStudentParentApplicationRequest input) {
        return Try.of(() -> {
                    Optional<Student> student = studentRepository.findByStudentPersonalNumber(input.getStudentNumber());
                    if (student.isEmpty()) {
                        return GetStudentParentApplicationResponse.builder()
                                .build();
                    }
                    List<Parent> parents = student.get().getParents();
                    List<ParentDTO> parentDTOS = parents.stream()
                            .map(parent -> ParentDTO.builder()
                                    .name(parent.getName())
                                    .parentType(ParentType.getByLabel(parent.getParentType()))
                                    .city(parent.getCity())
                                    .phoneNumber(parent.getPhoneNumber())
                                    .street(parent.getStreet())
                                    .streetNumber(parent.getStreetNumber())
                                    .build())
                            .toList();
                    return GetStudentParentApplicationResponse.builder()
                            .parents(parentDTOS)
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
