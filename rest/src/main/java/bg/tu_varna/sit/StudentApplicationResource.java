package bg.tu_varna.sit;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/apply")
public class StudentApplicationResource {
    @POST
    public Response saveStudentData(){
        return Response.ok().build();
    }
}
