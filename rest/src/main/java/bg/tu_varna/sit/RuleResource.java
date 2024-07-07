package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.rule.GetRulesRequest;
import bg.tu_varna.sit.model.rule.GetRulesResponse;
import bg.tu_varna.sit.model.rule.UpdateRulesRequest;
import bg.tu_varna.sit.model.rule.UpdateRulesResponse;
import bg.tu_varna.sit.operation.rules.GetRulesOperation;
import bg.tu_varna.sit.operation.rules.UpdateRulesOperation;
import io.quarkus.logging.Log;
import io.vavr.control.Either;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/rule")
@RequiredArgsConstructor
public class RuleResource {
    private final GetRulesOperation getRulesOperation;
    private final UpdateRulesOperation updateRulesOperation;

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response updateRules(UpdateRulesRequest request){
        Log.info("Received update request for rules: " + request);
        Either<Error, UpdateRulesResponse> process = updateRulesOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }

    @GET
    @Path("/get")
    @RolesAllowed("admin")
    public Response getRules() {
        Log.info("Processing getRules request");
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
