package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomRepository implements PanacheRepositoryBase<Room, Long> {
    public Room findByRoomNumberAndDormitory(String roomNumber, Long dormitoryId) {
        return find("roomNumber = ?1 and dormitory.id = ?2", roomNumber, dormitoryId).firstResult();
    }
}
