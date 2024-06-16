package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.RoomOccupancy;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomOccupancyRepository implements PanacheRepositoryBase<RoomOccupancy, Long> {
}
