package bg.tu_varna.sit.model.application.documents;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateDocumentResponse implements OperationResponse {
    private String message;
}
