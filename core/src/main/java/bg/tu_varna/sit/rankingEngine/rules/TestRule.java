package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.attributes.AStudentGrade;
import bg.tu_varna.sit.rankingEngine.attributes.AExcellentGrade;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import bg.tu_varna.sit.rankingEngine.context.Context;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RuleQualifier("TestRule")
@RequiredArgsConstructor
public class TestRule implements Rule {
    private final AStudentGrade studentGrade;
    private final AExcellentGrade studentGradeThreshold;

    @Override
    public Boolean evaluate(Context context) {
        Double grade = studentGrade.getAttributeValue(context);
        Optional<Double> studentGrade = studentGradeThreshold.getAttributeValue(context);
        return studentGrade.filter(aDouble -> grade >= aDouble).isPresent();
    }
}
