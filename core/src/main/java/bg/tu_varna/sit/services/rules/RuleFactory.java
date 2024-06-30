package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class RuleFactory {
    private final RuleLoader ruleLoader;
//    @Inject
//    @Any
//    Instance<Rule> rules;

    //    public RuleFactory() {
//        rules.forEach(rule -> {
//            System.out.println("Available rule: " + rule.getClass().getName());
//        });
//    }
    public Rule mapRule(String ruleName) {
        Rule rule = ruleLoader.getRule(ruleName);
        if (rule == null) {
            throw new IllegalArgumentException("No rule found for name: " + ruleName);
        }
        return rule;
    }

//    public Rule mapRule(String ruleName) {
//        System.out.println("Trying to map rule: " + ruleName);
//        Instance<Rule> selectedRules = rules.select(new RuleQualifierLiteral(ruleName));
//        selectedRules.forEach(rule -> System.out.println("Found rule: " + rule.getClass().getName()));
//        if (selectedRules.isAmbiguous()) {
//            throw new AmbiguousResolutionException("Ambiguous beans for rule: " + ruleName);
//        } else if (selectedRules.isUnsatisfied()) {
//            throw new UnsatisfiedResolutionException("No beans found for rule: " + ruleName);
//        }
//        return selectedRules.get();
//    }

    public List<Rule> mapRules(List<String> ruleNames) {
        return ruleNames.stream()
                .map(this::mapRule)
                .toList();
    }
}
