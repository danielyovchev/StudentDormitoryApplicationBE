package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.rankingEngine.attributes.AHavingChild;
import bg.tu_varna.sit.rankingEngine.attributes.AStudentDocument;
import bg.tu_varna.sit.rankingEngine.context.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class Rule001Test {

    @Mock
    private AHavingChild aHavingChild;

    @Mock
    private AStudentDocument studentDocument;

    @InjectMocks
    private Rule001 rule001;

    private Context context;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the context object
        context = new Context();
    }

    @Test
    public void testEvaluateWithChildAndValidDocuments() {
        // Arrange
        List<Document> documents = Arrays.asList(
                new Document(1L, "url1", "unique1", "file1", true, bg.tu_varna.sit.enums.DocumentEnum.CHILD, LocalDate.now(), LocalDate.now(), null),
                new Document(2L, "url2", "unique2", "file2", true, bg.tu_varna.sit.enums.DocumentEnum.FOREIGN, LocalDate.now(), LocalDate.now(), null)
        );

        when(aHavingChild.getAttributeValue(context)).thenReturn(true);
        when(studentDocument.getAttributeValue(context)).thenReturn(documents);

        // Act
        Boolean result = rule001.evaluate(context);

        // Assert
        assertTrue(result);
        verify(aHavingChild, times(1)).getAttributeValue(context);
        verify(studentDocument, times(1)).getAttributeValue(context);
    }

    @Test
    public void testEvaluateWithoutChild() {
        // Arrange
        List<Document> documents = Arrays.asList(
                new Document(1L, "url1", "unique1", "file1", true, bg.tu_varna.sit.enums.DocumentEnum.CHILD, LocalDate.now(), LocalDate.now(), null),
                new Document(2L, "url2", "unique2", "file2", true, bg.tu_varna.sit.enums.DocumentEnum.FOREIGN, LocalDate.now(), LocalDate.now(), null)
        );

        when(aHavingChild.getAttributeValue(context)).thenReturn(false);
        when(studentDocument.getAttributeValue(context)).thenReturn(documents);

        // Act
        Boolean result = rule001.evaluate(context);

        // Assert
        assertFalse(result);
        verify(aHavingChild, times(1)).getAttributeValue(context);
        verify(studentDocument, times(1)).getAttributeValue(context);
    }

    @Test
    public void testEvaluateWithInvalidDocuments() {
        // Arrange
        List<Document> documents = Arrays.asList(
                new Document(1L, "url1", "unique1", "file1", true, bg.tu_varna.sit.enums.DocumentEnum.PARENTDEATH, LocalDate.now(), LocalDate.now(), null),
                new Document(2L, "url2", "unique2", "file2", true, bg.tu_varna.sit.enums.DocumentEnum.REDUCEDABILITY, LocalDate.now(), LocalDate.now(), null)
        );

        when(aHavingChild.getAttributeValue(context)).thenReturn(true);
        when(studentDocument.getAttributeValue(context)).thenReturn(documents);

        // Act
        Boolean result = rule001.evaluate(context);

        // Assert
        assertFalse(result);
        verify(aHavingChild, times(1)).getAttributeValue(context);
        verify(studentDocument, times(1)).getAttributeValue(context);
    }

    @Test
    public void testEvaluateWithoutChildAndInvalidDocuments() {
        // Arrange
        List<Document> documents = Arrays.asList(
                new Document(1L, "url1", "unique1", "file1", true, bg.tu_varna.sit.enums.DocumentEnum.HOMELESS, LocalDate.now(), LocalDate.now(), null),
                new Document(2L, "url2", "unique2", "file2", true, bg.tu_varna.sit.enums.DocumentEnum.HOMELESS, LocalDate.now(), LocalDate.now(), null)
        );

        when(aHavingChild.getAttributeValue(context)).thenReturn(false);
        when(studentDocument.getAttributeValue(context)).thenReturn(documents);

        // Act
        Boolean result = rule001.evaluate(context);

        // Assert
        assertFalse(result);
        verify(aHavingChild, times(1)).getAttributeValue(context);
        verify(studentDocument, times(1)).getAttributeValue(context);
    }
}

// Mock class for DocumentEnum for testing purposes
enum DocumentEnum {
    CHILD,
    OTHER
}