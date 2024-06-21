package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifierLiteral;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RuleFactory {
    @Inject
    @Any
    Instance<Rule> rules;

    public Rule mapRule(String ruleName) {
        return rules.select(new RuleQualifierLiteral(ruleName)).get();
    }

    public List<Rule> mapRules(List<String> ruleNames) {
        return ruleNames.stream()
                .map(this::mapRule)
                .toList();
    }
}
