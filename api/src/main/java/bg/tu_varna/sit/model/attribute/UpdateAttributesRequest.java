package bg.tu_varna.sit.model.attribute;

import bg.tu_varna.sit.base.OperationInput;
import bg.tu_varna.sit.model.dto.AttributeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttributesRequest implements OperationInput {
    private List<AttributeDTO> attributes;
}
