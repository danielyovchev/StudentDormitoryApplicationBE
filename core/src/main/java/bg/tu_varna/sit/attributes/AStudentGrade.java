package bg.tu_varna.sit.attributes;

import bg.tu_varna.sit.interfaces.Attribute;
import bg.tu_varna.sit.services.context.Context;
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
