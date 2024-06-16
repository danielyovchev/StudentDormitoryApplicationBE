package bg.tu_varna.sit;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.attribute.GetAttributesRequest;
import bg.tu_varna.sit.model.attribute.GetAttributesResponse;
import bg.tu_varna.sit.model.attribute.UpdateAttributesRequest;
import bg.tu_varna.sit.model.attribute.UpdateAttributesResponse;
import bg.tu_varna.sit.operation.attribute.GetAttributesOperation;
import bg.tu_varna.sit.operation.attribute.UpdateAttributesOperation;
import io.vavr.control.Either;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/attributes")
@RequiredArgsConstructor
public class AttributeResource {
    private final GetAttributesOperation getAttributesOperation;
    private final UpdateAttributesOperation updateAttributesOperation;

    @PUT
    @Path("/update")
    public Response updateAttributes(UpdateAttributesRequest request) {
        Either<Error, UpdateAttributesResponse> process = updateAttributesOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getMessage()).build();
    }

    @GET
    @Path("/get")
    public Response getAttributes() {
        GetAttributesRequest request = new GetAttributesRequest();
        Either<Error, GetAttributesResponse> process = getAttributesOperation.process(request);
        if (process.isLeft()) {
            return Response.status(process.getLeft().getStatusCode())
                    .entity(process.getLeft().getMessage())
                    .build();
        }
        return Response.ok(process.get().getAttributes()).build();
    }
}
