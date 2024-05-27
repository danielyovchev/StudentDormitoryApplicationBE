package bg.tu_varna.sit.attributes;

import bg.tu_varna.sit.interfaces.Attribute;
import bg.tu_varna.sit.repository.AttributeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class AHavingChild implements Attribute {
    private final AttributeRepository attributeRepository;
    @Override
    public Object getAttributeValue() {
        Boolean havingChild = attributeRepository.find("");
        return List.of();
    }
}
