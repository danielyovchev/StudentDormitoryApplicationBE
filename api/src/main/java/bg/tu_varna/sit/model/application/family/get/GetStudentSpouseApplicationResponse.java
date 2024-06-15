package bg.tu_varna.sit.model.application.family.get;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.ParentDTO;
import bg.tu_varna.sit.model.dto.SpouseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStudentSpouseApplicationResponse implements OperationResponse {
    private SpouseDTO spouse;
}
