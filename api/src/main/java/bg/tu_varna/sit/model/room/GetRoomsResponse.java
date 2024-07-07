package bg.tu_varna.sit.model.room;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomsResponse implements OperationResponse {
    private Map<String, List<String>> rooms;
}
