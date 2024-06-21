package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.attributes.AHavingChild;
import bg.tu_varna.sit.rankingEngine.attributes.ANoSpouse;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
@RuleQualifier("Rule002")
public class Rule002 implements Rule {
    private final AHavingChild aHavingChild;
    private final ANoSpouse aNoSpouse;

    @Override
    public Boolean evaluate(Context context) {
        Boolean havingChildAttribute = aHavingChild.getAttributeValue(context);
        Boolean noSpouseAttribute = aNoSpouse.getAttributeValue(context);
        return havingChildAttribute && noSpouseAttribute;
    }
}
