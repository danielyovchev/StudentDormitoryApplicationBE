package bg.tu_varna.sit.error;

import bg.tu_varna.sit.base.Error;
import jakarta.ws.rs.core.Response;

public class InternalError implements Error {
    @Override
    public Response.Status getStatusCode() {
        return Response.Status.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "Internal Server Error";
    }
}
