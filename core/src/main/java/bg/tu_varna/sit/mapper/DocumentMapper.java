package bg.tu_varna.sit.mapper;

import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.model.dto.DocumentDTO;
import bg.tu_varna.sit.model.enums.DocumentType;
import bg.tu_varna.sit.service.AzureBlobService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class DocumentMapper {
    private final AzureBlobService azureBlobService;
    public DocumentDTO toDocumentDTO(Document document) {
        final String fullUrl = document.getFileUrl() + "?" +
                azureBlobService.getSasToken(document.getUniqueName());
        return DocumentDTO.builder()
                .fileUrl(fullUrl)
                .fileName(document.getFileName())
                .studentNumber(document.getStudent().getPersonalNumber())
                .studentName(document.getStudent().getName())
                .documentType(DocumentType.valueOf(String.valueOf(document.getDocumentEnum().toString())))
                .build();
    }
}
