package bg.tu_varna.sit.rankingEngine.attributes;

import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Attribute;
import bg.tu_varna.sit.repository.AttributeRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class ALowExcellentGrade implements Attribute<Optional<Double>> {
    private final AttributeRepository attributeRepository;
    @Override
    public Optional<Double> getAttributeValue(Context context) {
        Optional<bg.tu_varna.sit.entity.Attribute> attribute = attributeRepository.find("name", ALowExcellentGrade.class.getSimpleName()).firstResultOptional();
        if (attribute.isEmpty()) {
            Log.warn("No attribute found: " + ALowExcellentGrade.class.getSimpleName());
            return Optional.empty();
        }
        String grade = attribute.get().getDefaultValue();
        if (Objects.isNull(grade)) {
            return Optional.empty();
        }
        return Optional.of(Double.parseDouble(grade));
    }
}
