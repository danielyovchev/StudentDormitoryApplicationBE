package bg.tu_varna.sit.model.rule;

import bg.tu_varna.sit.base.OperationInput;
import bg.tu_varna.sit.model.dto.RuleDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRulesRequest implements OperationInput {
    private List<RuleDTO> rules;
}
