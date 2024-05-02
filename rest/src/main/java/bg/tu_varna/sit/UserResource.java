package bg.tu_varna.sit;

import bg.tu_varna.sit.security.KeycloakService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
@Authenticated
public class UserResource {
    @Inject
    KeycloakService keycloakService;
    @GET
    @Path("/example")
    @PermitAll
    public Response exampleEndpoint(){
        return Response.ok("Good").build();
    }
    @GET
    @Path("/example/admin")
    @RolesAllowed("admin")
    public Response exampleEndpointAutg(){
        return Response.ok("Good").build();
    }
}
