package bg.tu_varna.sit.rules;

import bg.tu_varna.sit.attributes.AHavingChild;
import bg.tu_varna.sit.attributes.A2;
import bg.tu_varna.sit.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class TestRule implements Rule {
    private final AHavingChild aHavingChild;
    private final A2 a2;
    @Override
    public Boolean evaluate() {
        return true;
    }
}
