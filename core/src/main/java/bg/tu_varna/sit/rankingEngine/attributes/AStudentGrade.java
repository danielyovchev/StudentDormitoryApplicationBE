package bg.tu_varna.sit.rankingEngine.attributes;

import bg.tu_varna.sit.rankingEngine.interfaces.Attribute;
import bg.tu_varna.sit.rankingEngine.context.Context;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class AStudentGrade implements Attribute<Double> {
    @Override
    public Double getAttributeValue(Context context) {
        return context.getStudent().getGrade();
    }
}
