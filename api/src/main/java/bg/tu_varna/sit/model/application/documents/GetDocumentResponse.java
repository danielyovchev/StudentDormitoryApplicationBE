package bg.tu_varna.sit.model.application.documents;

import bg.tu_varna.sit.base.OperationResponse;
import bg.tu_varna.sit.model.dto.DocumentDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDocumentResponse implements OperationResponse {
    private List<DocumentDTO> documents;
}
