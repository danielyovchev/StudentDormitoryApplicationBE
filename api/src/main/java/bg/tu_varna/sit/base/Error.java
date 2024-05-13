package bg.tu_varna.sit.base;

import jakarta.ws.rs.core.Response;

public interface Error {
    Response.Status getStatusCode();
    String getMessage();
}
