package bg.tu_varna.sit.model.application.documents;

import bg.tu_varna.sit.base.OperationInput;
import jakarta.ws.rs.FormParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadDocumentRequest implements OperationInput {
    //@FormParam("file")
    @RestForm("file")
    @Schema(type = SchemaType.STRING, format = "binary", description = "The file to upload")
    private FileUpload fileUpload;
}
