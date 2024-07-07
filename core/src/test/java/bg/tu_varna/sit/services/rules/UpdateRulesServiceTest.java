package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.RuleEntity;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.dto.RuleDTO;
import bg.tu_varna.sit.model.rule.UpdateRulesRequest;
import bg.tu_varna.sit.model.rule.UpdateRulesResponse;
import bg.tu_varna.sit.repository.RuleRepository;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UpdateRulesServiceTest {

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private UpdateRulesService updateRulesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testProcessSuccess() {
        // Arrange
        RuleDTO ruleDTO1 = RuleDTO.builder()
                .id(1L)
                .defaultScore(15)
                .isActive(true)
                .build();
        RuleDTO ruleDTO2 = RuleDTO.builder()
                .id(2L)
                .defaultScore(25)
                .isActive(false)
                .build();

        UpdateRulesRequest request = UpdateRulesRequest.builder()
                .rules(List.of(ruleDTO1, ruleDTO2))
                .build();

        RuleEntity ruleEntity1 = new RuleEntity(1L, "Rule1", "Description1", 10, true);
        RuleEntity ruleEntity2 = new RuleEntity(2L, "Rule2", "Description2", 20, false);

        when(ruleRepository.findById(1L)).thenReturn(ruleEntity1);
        when(ruleRepository.findById(2L)).thenReturn(ruleEntity2);

        // Act
        Either<Error, UpdateRulesResponse> response = updateRulesService.process(request);

        // Assert
        assertTrue(response.isRight());
        UpdateRulesResponse updateRulesResponse = response.get();
        assertEquals("Rules are updated", updateRulesResponse.getMessage());

        verify(ruleRepository, times(1)).findById(1L);
        verify(ruleRepository, times(1)).findById(2L);
        verify(ruleRepository, times(2)).persist(any(RuleEntity.class));
    }

    @Test
    @Transactional
    public void testProcessFailure() {
        // Arrange
        RuleDTO ruleDTO = RuleDTO.builder()
                .id(1L)
                .defaultScore(15)
                .isActive(true)
                .build();

        UpdateRulesRequest request = UpdateRulesRequest.builder()
                .rules(List.of(ruleDTO))
                .build();

        when(ruleRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));

        // Act
        Either<Error, UpdateRulesResponse> response = updateRulesService.process(request);

        // Assert
        assertTrue(response.isLeft());
        Error error = response.getLeft();
        assertTrue(error instanceof InternalError);

        verify(ruleRepository, times(1)).findById(1L);
        verify(ruleRepository, never()).persist(any(RuleEntity.class));
    }
}
