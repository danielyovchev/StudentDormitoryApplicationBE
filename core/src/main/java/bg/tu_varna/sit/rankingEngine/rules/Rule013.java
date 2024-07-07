package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.attributes.AAverageGrade;
import bg.tu_varna.sit.rankingEngine.attributes.AStudentGrade;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
@RuleQualifier("Rule013")
public class Rule013 implements Rule {
    private final AAverageGrade averageGrade;
    private final AStudentGrade studentGrade;

    @Override
    public Boolean evaluate(Context context) {
        Double grade = studentGrade.getAttributeValue(context);
        Optional<Double> threshold = averageGrade.getAttributeValue(context);
        return threshold.filter(aDouble -> grade <= aDouble).isPresent();
    }
}
