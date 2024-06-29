package bg.tu_varna.sit.model.application.documents;

import bg.tu_varna.sit.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateDocumentRequest implements OperationInput {
    private Long documentId;
}
