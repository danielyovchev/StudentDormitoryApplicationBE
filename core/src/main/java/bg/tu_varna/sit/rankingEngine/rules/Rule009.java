package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.attributes.AExcellentGrade;
import bg.tu_varna.sit.rankingEngine.attributes.ALowExcellentGrade;
import bg.tu_varna.sit.rankingEngine.attributes.AStudentGrade;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
@RuleQualifier("Rule009")
public class Rule009 implements Rule {
    private final AStudentGrade studentGrade;
    private final ALowExcellentGrade lowExcellentGrade;
    private final AExcellentGrade studentGradeThreshold;
    @Override
    public Boolean evaluate(Context context) {
        Double grade = studentGrade.getAttributeValue(context);
        Optional<Double> threshold = lowExcellentGrade.getAttributeValue(context);
        Optional<Double> upperThreshold = studentGradeThreshold.getAttributeValue(context);
        return upperThreshold
                .filter(value -> threshold
                        .filter(aDouble -> grade >= aDouble && grade < value)
                        .isPresent())
                .isPresent();

    }
}
