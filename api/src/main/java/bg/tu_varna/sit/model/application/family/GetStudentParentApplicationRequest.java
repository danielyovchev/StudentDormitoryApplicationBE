package bg.tu_varna.sit.model.application.family;

import bg.tu_varna.sit.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetStudentParentApplicationRequest implements OperationInput {
    private String studentId;
}
