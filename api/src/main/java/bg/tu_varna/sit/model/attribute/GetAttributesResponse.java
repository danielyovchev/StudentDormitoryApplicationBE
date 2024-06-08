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
public class GetAttributesResponse implements OperationResponse {
    private List<AttributeDTO> attributes;
}
