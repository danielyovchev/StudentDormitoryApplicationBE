package bg.tu_varna.sit.model.application.student;

import bg.tu_varna.sit.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetStudentApplicationRequest implements OperationInput {
    private String studentId;
}
