package bg.tu_varna.sit.model.application.unverified;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.ApplicationDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUnverifiedStudentsResponse implements OperationResponse {
    private List<ApplicationDTO> applications;
}
