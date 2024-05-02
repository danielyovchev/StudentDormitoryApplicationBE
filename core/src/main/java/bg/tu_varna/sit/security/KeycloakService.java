package bg.tu_varna.sit.security;

import jakarta.enterprise.context.ApplicationScoped;
import org.keycloak.representations.AccessTokenResponse;

@ApplicationScoped
public class KeycloakService {
    public AccessTokenResponse getTokensForUser(String username, String password){
        return null;
    }
}
