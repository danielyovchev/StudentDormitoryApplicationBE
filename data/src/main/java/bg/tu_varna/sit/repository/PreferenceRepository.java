package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Preference;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PreferenceRepository implements PanacheRepositoryBase<Preference, Long> {
}
