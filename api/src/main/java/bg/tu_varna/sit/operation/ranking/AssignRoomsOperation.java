package bg.tu_varna.sit.operation.ranking;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.ranking.AssignRoomRequest;
import bg.tu_varna.sit.model.ranking.AssignRoomResponse;
import io.vavr.control.Either;

public interface AssignRoomsOperation extends OperationProcessor<AssignRoomRequest, AssignRoomResponse> {
    @Override
    Either<Error, AssignRoomResponse> process(AssignRoomRequest input);
}
