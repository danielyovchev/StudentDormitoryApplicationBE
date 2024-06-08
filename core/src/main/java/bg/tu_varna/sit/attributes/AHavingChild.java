package bg.tu_varna.sit.attributes;

import bg.tu_varna.sit.interfaces.Attribute;
import bg.tu_varna.sit.repository.AttributeRepository;
import bg.tu_varna.sit.services.context.Context;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class AHavingChild implements Attribute<Boolean> {
    private final AttributeRepository attributeRepository;

    @Override
    public Boolean getAttributeValue(Context context) {
        //Boolean havingChild = attributeRepository.find("");
        return true;
    }
}
