package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Child;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class ChildRepository implements PanacheRepositoryBase<Child, UUID> {
}
