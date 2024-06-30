package bg.tu_varna.sit.error;

import bg.tu_varna.sit.base.Error;
import jakarta.ws.rs.core.Response;

public class InternalError implements Error {
    @Override
    public Response.Status getStatusCode() {
        return Response.Status.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "Service unavailable";
    }
}
