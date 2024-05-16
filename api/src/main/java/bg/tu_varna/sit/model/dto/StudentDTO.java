package bg.tu_varna.sit.model.dto;

import bg.tu_varna.sit.model.enums.EducationForm;
import bg.tu_varna.sit.model.enums.Faculty;
import bg.tu_varna.sit.model.enums.Specialty;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String name;
    private EducationForm educationForm;
    private Faculty faculty;
    private Specialty specialty;
    private String city;
    private String municipality;
    private String street;
    private Integer streetNumber;
    private Integer entrance;
    private Integer apartment;
    private String personalNumber;
    private String phoneNumber;
    private Double grade;
    private Integer dormitoryNumber;
    private Integer roomNumber;
}
