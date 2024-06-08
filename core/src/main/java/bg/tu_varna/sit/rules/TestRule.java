package bg.tu_varna.sit.rules;

import bg.tu_varna.sit.annotations.RuleQualifier;
import bg.tu_varna.sit.attributes.AStudentGrade;
import bg.tu_varna.sit.attributes.AStudentGradeThreshold;
import bg.tu_varna.sit.interfaces.Rule;
import bg.tu_varna.sit.services.context.Context;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RuleQualifier("TestRule")
@RequiredArgsConstructor
public class TestRule implements Rule {
    private final AStudentGrade studentGrade;
    private final AStudentGradeThreshold studentGradeThreshold;

    @Override
    public Boolean evaluate(Context context) {
        Double grade = studentGrade.getAttributeValue(context);
        Double studentGrade = studentGradeThreshold.getAttributeValue(context);
        return grade >= studentGrade;
    }
}
