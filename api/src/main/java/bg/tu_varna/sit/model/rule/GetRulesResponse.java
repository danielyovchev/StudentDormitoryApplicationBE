package bg.tu_varna.sit.model.rule;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.RuleDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRulesResponse implements OperationResponse {
    private List<RuleDTO> rules;
}
