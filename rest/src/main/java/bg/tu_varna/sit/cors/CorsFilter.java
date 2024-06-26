package bg.tu_varna.sit.cors;

import io.quarkus.logging.Log;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {
        Log.info("Executing CORS filter");
        //containerResponseContext.getHeaders().remove("Access-Control-Allow-Origin");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
