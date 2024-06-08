package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.rule.GetRulesRequest;
import bg.tu_varna.sit.model.rule.GetRulesResponse;
import bg.tu_varna.sit.model.rule.UpdateRulesRequest;
import bg.tu_varna.sit.model.rule.UpdateRulesResponse;
import bg.tu_varna.sit.operation.rules.GetRulesOperation;
import bg.tu_varna.sit.operation.rules.UpdateRulesOperation;
import io.vavr.control.Either;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/rule")
@RequiredArgsConstructor
public class RuleResource {
    private final GetRulesOperation getRulesOperation;
    private final UpdateRulesOperation updateRulesOperation;

    @PUT
    @Path("/update")
    public Response updateRules(UpdateRulesRequest request){
        Either<Error, UpdateRulesResponse> process = updateRulesOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getMessage()).build();
    }

    @GET
    @Path("/get")
    public Response getRules() {
        GetRulesRequest request = new GetRulesRequest();
        Either<Error, GetRulesResponse> process = getRulesOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getRules()).build();
    }
}
