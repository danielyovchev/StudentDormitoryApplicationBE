package bg.tu_varna.sit.model.application.family.get;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentParentApplicationResponse implements OperationResponse {
    private String message;
}
