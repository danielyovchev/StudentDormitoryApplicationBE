package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.attributes.AAverageGrade;
import bg.tu_varna.sit.rankingEngine.attributes.AGoodGrade;
import bg.tu_varna.sit.rankingEngine.attributes.AStudentGrade;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
@RuleQualifier("Rule012")
public class Rule012 implements Rule {
    private final AStudentGrade studentGrade;
    private final AAverageGrade averageGrade;
    private final AGoodGrade goodGrade;
    @Override
    public Boolean evaluate(Context context) {
        Double grade = studentGrade.getAttributeValue(context);
        Optional<Double> threshold = averageGrade.getAttributeValue(context);
        Optional<Double> upperThreshold = goodGrade.getAttributeValue(context);
        return upperThreshold
                .filter(value -> threshold
                        .filter(aDouble -> grade >= aDouble && grade < value)
                        .isPresent())
                .isPresent();
    }
}
