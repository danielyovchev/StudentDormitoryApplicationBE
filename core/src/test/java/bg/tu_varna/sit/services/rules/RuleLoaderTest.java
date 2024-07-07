package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.inject.Instance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleLoaderTest {

    @Mock
    private Instance<Rule> rules;

    private Rule rule1;
    private Rule rule2;

    private RuleLoader ruleLoader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking Rule instances
        rule1 = mock(RuleWithQualifier1.class);
        rule2 = mock(RuleWithQualifier2.class);

        // Mocking the behavior of the rules Instance
        Set<Rule> ruleSet = new HashSet<>();
        ruleSet.add(rule1);
        ruleSet.add(rule2);

        Iterator<Rule> ruleIterator = ruleSet.iterator();
        when(rules.iterator()).thenReturn(ruleIterator);

        // Initializing RuleLoader with mocked rules
        ruleLoader = new RuleLoader(rules);
    }

    @Test
    public void testGetRule() {
        // Testing getRule method
        Rule result1 = ruleLoader.getRule("rule1");
        assertEquals(rule1, result1);

        Rule result2 = ruleLoader.getRule("rule2");
        assertEquals(rule2, result2);

        Rule result3 = ruleLoader.getRule("rule3");
        assertNull(result3);
    }

    // Mock implementations of Rule with RuleQualifier annotations for testing
    @RuleQualifier("rule1")
    private static class RuleWithQualifier1 implements Rule {
        // Rule implementation

        @Override
        public Boolean evaluate(Context context) {
            return true;
        }
    }

    @RuleQualifier("rule2")
    private static class RuleWithQualifier2 implements Rule {
        // Rule implementation

        @Override
        public Boolean evaluate(Context context) {
            return false;
        }
    }
}