package bg.tu_varna.sit.attributes;

import bg.tu_varna.sit.interfaces.Attribute;
import bg.tu_varna.sit.services.context.Context;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class A2 implements Attribute<List<String>> {
    @Override
    public List<String> getAttributeValue(Context context) {
        return List.of();
    }
}
