package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Attribute;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AttributeRepository implements PanacheRepositoryBase<Attribute, Long> {
}
