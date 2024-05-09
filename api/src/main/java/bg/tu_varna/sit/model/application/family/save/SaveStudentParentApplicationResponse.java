package bg.tu_varna.sit.model.application.family.save;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveStudentParentApplicationResponse implements OperationResponse {
    private String message;
}
