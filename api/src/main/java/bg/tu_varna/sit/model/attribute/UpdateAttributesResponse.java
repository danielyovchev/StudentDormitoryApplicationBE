package bg.tu_varna.sit.model.attribute;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttributesResponse implements OperationResponse {
    private String message;
}
