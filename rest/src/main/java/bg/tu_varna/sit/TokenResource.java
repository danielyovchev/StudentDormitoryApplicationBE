package bg.tu_varna.sit;

import bg.tu_varna.sit.service.AzureBlobService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("token")
@RequiredArgsConstructor
public class TokenResource {
    private final AzureBlobService azureBlobService;
    @GET
    @Path("/blob/{blobName}")
    public Response getAzureToken (@PathParam("blobName") String blobName) {
        String token = azureBlobService.getSasToken(blobName);
        return Response.ok(token).build();
    }
}
