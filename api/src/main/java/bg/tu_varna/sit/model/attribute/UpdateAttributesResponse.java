package bg.tu_varna.sit.model.attribute;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.AttributeDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttributesResponse implements OperationResponse {
    private String message;
}
