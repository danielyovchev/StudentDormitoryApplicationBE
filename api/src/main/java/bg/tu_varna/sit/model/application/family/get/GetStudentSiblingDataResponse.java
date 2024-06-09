package bg.tu_varna.sit.model.application.family.get;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.SiblingDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStudentSiblingDataResponse implements OperationResponse {
    private List<SiblingDTO> siblings;
}
