package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.ranking.RankStudentsRequest;
import bg.tu_varna.sit.model.ranking.RankStudentsResponse;
import bg.tu_varna.sit.services.rules.RankStudentService;
import io.vavr.control.Either;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/rank")
@RequiredArgsConstructor
public class RankResource {
    private final RankStudentService rankStudentService;

    @POST
    @Path("/calculate")
    public Response calculate(RankStudentsRequest request) {
        Either<Error, RankStudentsResponse> process = rankStudentService.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getMessage()).build();
    }
}
