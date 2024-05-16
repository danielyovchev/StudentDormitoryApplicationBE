package bg.tu_varna.sit.model.application.documents;

import bg.tu_varna.sit.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadDocumentRequest implements OperationInput {
    private FileUpload fileUpload;
}
