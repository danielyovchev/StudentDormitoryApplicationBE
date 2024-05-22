package bg.tu_varna.sit.model.application.family.save;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveStudentChildDataResponse implements OperationResponse {
    private String message;
}
