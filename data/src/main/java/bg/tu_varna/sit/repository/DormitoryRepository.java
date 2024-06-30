package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Dormitory;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DormitoryRepository implements PanacheRepositoryBase<Dormitory, Long> {
    public Dormitory findByName(String name) {
        return find("name=?1", name).firstResult();
    }
}
