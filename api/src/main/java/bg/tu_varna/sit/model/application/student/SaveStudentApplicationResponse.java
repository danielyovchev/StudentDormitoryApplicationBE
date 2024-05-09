package bg.tu_varna.sit.model.application.student;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveStudentApplicationResponse implements OperationResponse {
    private String message;
}
