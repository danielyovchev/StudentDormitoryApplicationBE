package bg.tu_varna.sit.model.application.student;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.StudentDTO;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStudentApplicationResponse implements OperationResponse {
    private StudentDTO studentDTO;
}
