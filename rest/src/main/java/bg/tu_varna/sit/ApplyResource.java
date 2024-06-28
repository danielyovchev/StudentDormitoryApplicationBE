package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.application.GetApplicationRequest;
import bg.tu_varna.sit.model.application.GetApplicationResponse;
import bg.tu_varna.sit.model.application.SaveApplicationRequest;
import bg.tu_varna.sit.model.application.SaveApplicationResponse;
import bg.tu_varna.sit.operation.application.GetStudentApplicationOperation;
import bg.tu_varna.sit.operation.application.SaveStudentApplicationOperation;
import io.vavr.control.Either;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/apply")
@RequiredArgsConstructor
public class ApplyResource {
    private final SaveStudentApplicationOperation saveStudentApplicationOperation;
    private final GetStudentApplicationOperation getStudentApplicationOperation;

    @POST
    public Response apply(SaveApplicationRequest request) {
        Either<Error, SaveApplicationResponse> process = saveStudentApplicationOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }

    @GET
    @Path("/get/{studentNumber}")
    public Response getApplication(@PathParam("studentNumber") String studentNumber) {
        GetApplicationRequest request = new GetApplicationRequest(studentNumber);
        Either<Error, GetApplicationResponse> process = getStudentApplicationOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get()).build();
    }
}
