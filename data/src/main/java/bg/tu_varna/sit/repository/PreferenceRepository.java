package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Preference;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PreferenceRepository implements PanacheRepositoryBase<Preference, Long> {
    public Optional<Preference> findStudentPreference(UUID studentId) {
        return find("student.id", studentId).firstResultOptional();
    }
}
