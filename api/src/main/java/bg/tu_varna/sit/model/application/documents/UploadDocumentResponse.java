package bg.tu_varna.sit.model.application.documents;

import bg.tu_varna.sit.base.OperationResponse;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadDocumentResponse implements OperationResponse {
    private String message;
}
