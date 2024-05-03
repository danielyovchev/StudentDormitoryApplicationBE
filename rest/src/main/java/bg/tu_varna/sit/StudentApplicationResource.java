package bg.tu_varna.sit;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/upload")
public class StudentApplicationResource {
    @POST
    @Path("/student/data")
    public Response saveStudentData(){
        return Response.ok().build();
    }

    @POST
    @Path("/student/family")
    public Response saveStudentFamilyData(){
        return Response.ok().build();
    }

    @POST
    @Path("/student/documents")
    public Response saveStudentDocument(){
        return Response.ok().build();
    }

    @GET
    @Path("/student/{studentId}/data")
    public Response getStudentData(String studentId){
        return Response.ok().build();
    }

    @GET
    @Path("/student/{studentId}/family")
    public Response getStudentFamilyData(String studentId){
        return Response.ok().build();
    }

    @GET
    @Path("/student/{studentId}/documents")
    public Response getStudentDocument(String studentId){
        return Response.ok().build();
    }
}
