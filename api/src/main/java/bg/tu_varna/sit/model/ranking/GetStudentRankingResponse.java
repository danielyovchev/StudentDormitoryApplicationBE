package bg.tu_varna.sit.model.ranking;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.RankingDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentRankingResponse implements OperationResponse {
    List<RankingDTO> ranking;
}
