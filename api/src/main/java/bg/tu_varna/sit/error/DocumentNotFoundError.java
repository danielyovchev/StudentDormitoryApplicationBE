package bg.tu_varna.sit.error;

import bg.tu_varna.sit.base.Error;
import jakarta.ws.rs.core.Response;

public class DocumentNotFoundError implements Error {
    @Override
    public Response.Status getStatusCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return "";
    }
}
