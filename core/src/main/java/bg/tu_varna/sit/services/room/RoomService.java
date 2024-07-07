package bg.tu_varna.sit.services.room;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Dormitory;
import bg.tu_varna.sit.entity.Room;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.room.GetRoomsRequest;
import bg.tu_varna.sit.model.room.GetRoomsResponse;
import bg.tu_varna.sit.operation.room.GetRoomsOperation;
import bg.tu_varna.sit.repository.DormitoryRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@RequiredArgsConstructor
public class RoomService implements GetRoomsOperation {
    private final DormitoryRepository dormitoryRepository;

    @Override
    public Either<Error, GetRoomsResponse> process(GetRoomsRequest input) {
        return Try.of(() -> {
                    List<Dormitory> dormitories = dormitoryRepository.listAll();
                    Map<String, List<String>> roomMap = new HashMap<>();
                    for (Dormitory dormitory : dormitories) {
                        List<String> roomNumbers = dormitory.getRooms().stream()
                                .map(Room::getRoomNumber)
                                .toList();
                        roomMap.put(dormitory.getName(), roomNumbers);
                    }
                    return GetRoomsResponse.builder()
                            .rooms(roomMap)
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());

    }
}
