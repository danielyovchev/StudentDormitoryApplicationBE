package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.attributes.AExcellentGrade;
import bg.tu_varna.sit.rankingEngine.attributes.AStudentGrade;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
@RuleQualifier("Rule008")
public class Rule008 implements Rule {
    private final AStudentGrade studentGrade;
    private final AExcellentGrade studentGradeThreshold;

    @Override
    public Boolean evaluate(Context context) {
        Double grade = studentGrade.getAttributeValue(context);
        Optional<Double> threshold = studentGradeThreshold.getAttributeValue(context);
        return threshold.filter(aDouble -> grade >= aDouble).isPresent();
    }
}
