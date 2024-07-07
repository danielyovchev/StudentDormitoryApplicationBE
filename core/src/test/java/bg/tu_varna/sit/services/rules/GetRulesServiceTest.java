package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.RuleEntity;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.dto.RuleDTO;
import bg.tu_varna.sit.model.rule.GetRulesRequest;
import bg.tu_varna.sit.model.rule.GetRulesResponse;
import bg.tu_varna.sit.repository.RuleRepository;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetRulesServiceTest {

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private GetRulesService getRulesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testProcessSuccess() {
        // Arrange
        RuleEntity rule1 = new RuleEntity(1L, "Rule1", "Description1", 10, true);
        RuleEntity rule2 = new RuleEntity(2L, "Rule2", "Description2", 20, false);

        when(ruleRepository.listAll()).thenReturn(Arrays.asList(rule1, rule2));

        GetRulesRequest request = new GetRulesRequest();

        // Act
        Either<Error, GetRulesResponse> response = getRulesService.process(request);

        // Assert
        assertTrue(response.isRight());
        GetRulesResponse rulesResponse = response.get();
        assertEquals(2, rulesResponse.getRules().size());

        RuleDTO dto1 = rulesResponse.getRules().get(0);
        assertEquals(1L, dto1.getId());
        assertEquals("Rule1", dto1.getName());
        assertEquals("Description1", dto1.getDescription());
        assertEquals(10, dto1.getDefaultScore());
        assertTrue(dto1.getIsActive());

        RuleDTO dto2 = rulesResponse.getRules().get(1);
        assertEquals(2L, dto2.getId());
        assertEquals("Rule2", dto2.getName());
        assertEquals("Description2", dto2.getDescription());
        assertEquals(20, dto2.getDefaultScore());
        assertFalse(dto2.getIsActive());

        verify(ruleRepository, times(1)).listAll();
    }

    @Test
    @Transactional
    public void testProcessFailure() {
        // Arrange
        when(ruleRepository.listAll()).thenThrow(new RuntimeException("Database error"));

        GetRulesRequest request = new GetRulesRequest();

        // Act
        Either<Error, GetRulesResponse> response = getRulesService.process(request);

        // Assert
        assertTrue(response.isLeft());
        Error error = response.getLeft();
        assertInstanceOf(InternalError.class, error);

        verify(ruleRepository, times(1)).listAll();
    }
}
