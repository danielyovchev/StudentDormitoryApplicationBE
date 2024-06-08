package bg.tu_varna.sit.attributes;

import bg.tu_varna.sit.interfaces.Attribute;
import bg.tu_varna.sit.repository.AttributeRepository;
import bg.tu_varna.sit.services.context.Context;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class AStudentGradeThreshold implements Attribute<Double> {
    private final AttributeRepository attributeRepository;

    @Override
    public Double getAttributeValue(Context context) {
        return 5.0;
    }
}
