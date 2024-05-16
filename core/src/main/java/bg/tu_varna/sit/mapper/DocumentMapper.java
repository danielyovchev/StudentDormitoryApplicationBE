package bg.tu_varna.sit.mapper;

import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.model.dto.DocumentDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentMapper {
    public DocumentDTO toDocumentDTO(Document document) {
        return DocumentDTO.builder()
                .file(document.getFile())
                .studentNumber(document.getStudent().getPersonalNumber())
                .studentName(document.getStudent().getName())
                .build();
    }
}
