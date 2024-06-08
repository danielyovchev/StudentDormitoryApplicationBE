package bg.tu_varna.sit.model.rule;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRulesResponse implements OperationResponse {
    private String message;
}
