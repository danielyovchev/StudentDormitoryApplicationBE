package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RuleFactoryTest {

    @Mock
    private RuleLoader ruleLoader;

    @InjectMocks
    private RuleFactory ruleFactory;

    @Mock
    private Rule rule1;

    @Mock
    private Rule rule2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapRuleSuccess() {
        // Arrange
        String ruleName = "rule1";
        when(ruleLoader.getRule(ruleName)).thenReturn(rule1);

        // Act
        Rule result = ruleFactory.mapRule(ruleName);

        // Assert
        assertEquals(rule1, result);
        verify(ruleLoader, times(1)).getRule(ruleName);
    }

    @Test
    public void testMapRuleFailure() {
        // Arrange
        String ruleName = "nonExistentRule";
        when(ruleLoader.getRule(ruleName)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleFactory.mapRule(ruleName);
        });
        assertEquals("No rule found for name: nonExistentRule", exception.getMessage());
        verify(ruleLoader, times(1)).getRule(ruleName);
    }

    @Test
    public void testMapRulesSuccess() {
        // Arrange
        List<String> ruleNames = Arrays.asList("rule1", "rule2");
        when(ruleLoader.getRule("rule1")).thenReturn(rule1);
        when(ruleLoader.getRule("rule2")).thenReturn(rule2);

        // Act
        List<Rule> result = ruleFactory.mapRules(ruleNames);

        // Assert
        assertEquals(2, result.size());
        assertEquals(rule1, result.get(0));
        assertEquals(rule2, result.get(1));
        verify(ruleLoader, times(1)).getRule("rule1");
        verify(ruleLoader, times(1)).getRule("rule2");
    }

    @Test
    public void testMapRulesWithNonExistentRule() {
        // Arrange
        List<String> ruleNames = Arrays.asList("rule1", "nonExistentRule");
        when(ruleLoader.getRule("rule1")).thenReturn(rule1);
        when(ruleLoader.getRule("nonExistentRule")).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleFactory.mapRules(ruleNames);
        });
        assertEquals("No rule found for name: nonExistentRule", exception.getMessage());
        verify(ruleLoader, times(1)).getRule("rule1");
        verify(ruleLoader, times(1)).getRule("nonExistentRule");
    }
}