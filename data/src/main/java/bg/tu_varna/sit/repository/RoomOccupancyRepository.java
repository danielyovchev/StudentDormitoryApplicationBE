package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.RoomOccupancy;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class RoomOccupancyRepository implements PanacheRepositoryBase<RoomOccupancy, Long> {
    public List<RoomOccupancy> findEndDateBeforeCurrentDate() {
        return list("endDate < ?1", LocalDate.now());
    }
}
