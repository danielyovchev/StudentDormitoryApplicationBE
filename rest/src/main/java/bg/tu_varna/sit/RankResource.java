package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.ranking.GetStudentRankingRequest;
import bg.tu_varna.sit.model.ranking.GetStudentRankingResponse;
import bg.tu_varna.sit.model.ranking.RankStudentsRequest;
import bg.tu_varna.sit.model.ranking.RankStudentsResponse;
import bg.tu_varna.sit.operation.ranking.GetStudentsRankingOperation;
import bg.tu_varna.sit.operation.ranking.RankStudentsOperation;
import io.vavr.control.Either;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/rank")
@RequiredArgsConstructor
public class RankResource {
    private final RankStudentsOperation rankStudentsOperation;
    private final GetStudentsRankingOperation getStudentsRankingOperation;

    @POST
    @Path("/calculate")
    public Response calculate(RankStudentsRequest request) {
        Either<Error, RankStudentsResponse> process = rankStudentsOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getMessage()).build();
    }

    @GET
    @Path("/ranking")
    public Response getRanking() {
        GetStudentRankingRequest request = new GetStudentRankingRequest();
        Either<Error, GetStudentRankingResponse> process = getStudentsRankingOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getRanking()).build();
    }
}
