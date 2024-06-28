package bg.tu_varna.sit.model.application;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.ApplicationDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetApplicationResponse implements OperationResponse {
    private ApplicationDTO applicationDTO;
}
