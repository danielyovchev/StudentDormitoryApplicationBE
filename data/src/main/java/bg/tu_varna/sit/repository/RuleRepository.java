package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.RuleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RuleRepository implements PanacheRepositoryBase<RuleEntity, Long> {
    public List<RuleEntity> findAllActiveRules() {
        return list("isActive", Boolean.TRUE);
    }
}
