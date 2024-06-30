package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class RuleLoader {
    private final Map<String, Rule> ruleMap = new HashMap<>();
    @Inject
    public RuleLoader(@Any Instance<Rule> rules) {
        for (Rule rule : rules) {
            Class<?> beanClass = getBeanClass(rule);
            RuleQualifier qualifier = beanClass.getAnnotation(RuleQualifier.class);
            if (qualifier != null) {
                Log.info("Found rule: " + qualifier.value() + " for class: " + beanClass.getName());
                ruleMap.put(qualifier.value(), rule);
            } else {
                Log.warn("No RuleQualifier found for class: " + beanClass.getName());
            }
        }
        Log.info("RuleLoader initialized with rules: " + ruleMap.keySet());
    }

    private Class<?> getBeanClass(Object bean) {
        Class<?> clazz = bean.getClass();
        if (clazz.getName().contains("ClientProxy")) {
            return clazz.getSuperclass();
        }
        return clazz;
    }

    public Rule getRule(String ruleName) {
        return ruleMap.get(ruleName);
    }
}
