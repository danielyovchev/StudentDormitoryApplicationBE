package bg.tu_varna.sit.model.dto;

import bg.tu_varna.sit.model.enums.DocumentType;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private Long id;
    private String fileUrl;
    private String fileName;
    private String studentName;
    private String studentNumber;
    private DocumentType documentType;
}
