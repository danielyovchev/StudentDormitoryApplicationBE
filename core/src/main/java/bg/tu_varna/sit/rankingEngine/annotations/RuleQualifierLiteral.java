package bg.tu_varna.sit.rankingEngine.annotations;

import jakarta.enterprise.util.AnnotationLiteral;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RuleQualifierLiteral extends AnnotationLiteral<RuleQualifier> implements RuleQualifier{
    private final String value;

    @Override
    public String value() {
        return value;
    }
}
