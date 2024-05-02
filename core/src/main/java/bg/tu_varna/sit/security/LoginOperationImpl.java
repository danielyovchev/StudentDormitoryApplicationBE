package bg.tu_varna.sit.security;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.model.security.LoginRequest;
import bg.tu_varna.sit.model.security.LoginResponse;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoginOperationImpl implements LoginOperation{
    @Override
    public Either<Error, LoginResponse> process(LoginRequest input) {
        return null;
    }
}
