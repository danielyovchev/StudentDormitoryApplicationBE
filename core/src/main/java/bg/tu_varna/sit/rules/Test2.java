package bg.tu_varna.sit.rules;

import bg.tu_varna.sit.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class Test2 implements Rule {
    @Override
    public Boolean evaluate() {
        return null;
    }
}
