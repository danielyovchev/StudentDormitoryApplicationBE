package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.room.GetRoomsRequest;
import bg.tu_varna.sit.model.room.GetRoomsResponse;
import bg.tu_varna.sit.operation.room.GetRoomsOperation;
import io.vavr.control.Either;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/rooms")
@RequiredArgsConstructor
public class RoomResource {
    private final GetRoomsOperation getRoomsOperation;

    @GET
    public Response getRooms() {
        GetRoomsRequest request = new GetRoomsRequest();
        Either<Error, GetRoomsResponse> process = getRoomsOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }
}
