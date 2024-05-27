package bg.tu_varna.sit.model.ranking;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankStudentsResponse implements OperationResponse {
    private String message;
}
