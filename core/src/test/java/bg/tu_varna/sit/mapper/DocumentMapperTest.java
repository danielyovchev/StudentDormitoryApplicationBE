package bg.tu_varna.sit.mapper;

import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.enums.DocumentEnum;
import bg.tu_varna.sit.model.dto.DocumentDTO;
import bg.tu_varna.sit.model.enums.DocumentType;
import bg.tu_varna.sit.service.AzureBlobService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class DocumentMapperTest {

    @Inject
    DocumentMapper documentMapper;

    @InjectMock
    AzureBlobService azureBlobService;

    private Document document;
    private String sasToken = "sasToken";

    @BeforeEach
    public void setUp() {
        Student student = new Student();
        student.setPersonalNumber("123456");
        student.setName("John Doe");

        document = new Document();
        document.setId(1L);
        document.setFileUrl("https://example.com/document");
        document.setFileName("document.pdf");
        document.setStudent(student);
        document.setDocumentEnum(DocumentEnum.CHILD);
        document.setUniqueName("uniqueName");

        Mockito.when(azureBlobService.getSasToken("uniqueName")).thenReturn(sasToken);
    }

    @Test
    public void testToDocumentDTO() {
        DocumentDTO documentDTO = documentMapper.toDocumentDTO(document);

        assertEquals(document.getId(), documentDTO.getId());
        assertEquals(document.getFileUrl() + "?" + sasToken, documentDTO.getFileUrl());
        assertEquals(document.getFileName(), documentDTO.getFileName());
        assertEquals(document.getStudent().getPersonalNumber(), documentDTO.getStudentNumber());
        assertEquals(document.getStudent().getName(), documentDTO.getStudentName());
        assertEquals(DocumentType.CHILD, documentDTO.getDocumentType());
    }
}
