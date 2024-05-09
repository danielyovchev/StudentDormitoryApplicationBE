package bg.tu_varna.sit.model.application.student;

import bg.tu_varna.sit.base.OperationInput;
import bg.tu_varna.sit.model.enums.EducationForm;
import bg.tu_varna.sit.model.enums.Faculty;
import bg.tu_varna.sit.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveStudentApplicationRequest implements OperationInput {
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
