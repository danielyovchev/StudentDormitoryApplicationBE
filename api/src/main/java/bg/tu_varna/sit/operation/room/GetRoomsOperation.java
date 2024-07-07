package bg.tu_varna.sit.operation.room;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.room.GetRoomsRequest;
import bg.tu_varna.sit.model.room.GetRoomsResponse;
import io.vavr.control.Either;

public interface GetRoomsOperation extends OperationProcessor<GetRoomsRequest, GetRoomsResponse> {
    @Override
    Either<Error, GetRoomsResponse> process(GetRoomsRequest input);
}
