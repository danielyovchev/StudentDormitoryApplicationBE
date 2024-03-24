package bg.tu_varna.sit;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
public class UserResource {
    @GET
    @Path("/example")
    public Response exampleEndpoint(){
        return Response.ok("Good").build();
    }
}
