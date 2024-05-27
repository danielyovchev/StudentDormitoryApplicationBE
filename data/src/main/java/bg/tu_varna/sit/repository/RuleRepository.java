package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Rule;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RuleRepository implements PanacheRepositoryBase<Rule, Long> {
    public List<Rule> findAllActiveRules() {
        return list("isActive", Boolean.TRUE);
    }
}
