package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomRepository implements PanacheRepositoryBase<Room, Long> {
}
