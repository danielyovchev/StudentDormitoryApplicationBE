package bg.tu_varna.sit.attributes;

import bg.tu_varna.sit.interfaces.Attribute;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class A2 implements Attribute {
    @Override
    public Object getAttributeValue() {
        return List.of();
    }
}
