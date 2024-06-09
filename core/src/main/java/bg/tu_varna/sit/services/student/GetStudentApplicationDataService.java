package bg.tu_varna.sit.services.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationResponse;
import bg.tu_varna.sit.model.dto.StudentDTO;
import bg.tu_varna.sit.operation.student.GetStudentApplicationDataOperation;
import bg.tu_varna.sit.repository.StudentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentApplicationDataService implements GetStudentApplicationDataOperation {
    private final StudentRepository studentRepository;
    @Override
    public Either<Error, GetStudentApplicationResponse> process(GetStudentApplicationRequest input) {
        return Try.of(() -> {
                    Optional<Student> student = studentRepository.findByStudentPersonalNumber(input.getStudentId());
                    if (student.isEmpty()) {
                        return GetStudentApplicationResponse.builder()
                                .build();
                    }
                    //TODO refine fields
                    StudentDTO studentDTO = StudentDTO.builder()
                            .name(student.get().getName())
                            .grade(student.get().getGrade())
                            .sex(student.get().getSex())
                            .apartment(student.get().getApartment())
                            .city(student.get().getCity())
                            .entrance(student.get().getEntrance())
                            .municipality(student.get().getMunicipality())
                            .build();
                    return GetStudentApplicationResponse.builder()
                            .studentDTO(studentDTO)
                            .build();
        })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
