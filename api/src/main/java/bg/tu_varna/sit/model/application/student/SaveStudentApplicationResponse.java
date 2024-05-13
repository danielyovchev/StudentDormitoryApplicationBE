package bg.tu_varna.sit.model.application.student;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SaveStudentApplicationResponse implements OperationResponse {
    private String message;
}
