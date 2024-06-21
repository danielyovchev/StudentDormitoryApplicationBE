package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
@RuleQualifier("Rule003")
public class Rule003 implements Rule {
    @Override
    public Boolean evaluate(Context context) {
        return null;
    }
}
