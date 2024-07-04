package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class RuleFactory {
    private final RuleLoader ruleLoader;

    public Rule mapRule(String ruleName) {
        Rule rule = ruleLoader.getRule(ruleName);
        if (rule == null) {
            throw new IllegalArgumentException("No rule found for name: " + ruleName);
        }
        return rule;
    }

    public List<Rule> mapRules(List<String> ruleNames) {
        return ruleNames.stream()
                .map(this::mapRule)
                .toList();
    }
}
