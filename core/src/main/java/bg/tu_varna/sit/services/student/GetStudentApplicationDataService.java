package bg.tu_varna.sit.services.student;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationRequest;
import bg.tu_varna.sit.model.application.student.GetStudentApplicationResponse;
import bg.tu_varna.sit.model.dto.StudentDTO;
import bg.tu_varna.sit.model.enums.Faculty;
import bg.tu_varna.sit.model.enums.Specialty;
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
                    StudentDTO studentDTO = StudentDTO.builder()
                            .name(student.get().getName())
                            .grade(student.get().getGrade())
                            .sex(student.get().getSex())
                            .city(student.get().getCity())
                            .municipality(student.get().getMunicipality())
                            .studentNumber(student.get().getStudentNumber())
                            .address(student.get().getAddress())
                            .personalId(student.get().getPersonalNumber())
                            .phoneNumber(student.get().getPhoneNumber())
                            .faculty(Faculty.valueOf(student.get().getFaculty()))
                            .specialty(Specialty.valueOf(student.get().getSpeciality()))
                            .examsToRetake(student.get().getExamsToRetake())
                            .build();
                    return GetStudentApplicationResponse.builder()
                            .studentDTO(studentDTO)
                            .build();
        })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
