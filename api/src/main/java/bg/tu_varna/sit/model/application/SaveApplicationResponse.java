package bg.tu_varna.sit.model.application;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveApplicationResponse implements OperationResponse {
    private String message;
}
