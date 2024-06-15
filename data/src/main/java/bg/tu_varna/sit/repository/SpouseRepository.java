package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Spouse;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class SpouseRepository implements PanacheRepositoryBase<Spouse, UUID> {
}
