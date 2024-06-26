package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Application;
import bg.tu_varna.sit.enums.ApplicationStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ApplicationRepository implements PanacheRepositoryBase<Application, Long> {
    public List<Application> findByUnverified() {
        return list("status", ApplicationStatus.PENDING);
    }
}
