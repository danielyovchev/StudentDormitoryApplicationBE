package bg.tu_varna.sit.security;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.security.LoginRequest;
import bg.tu_varna.sit.model.security.LoginResponse;
import io.vavr.control.Either;

public interface LoginOperation extends OperationProcessor<LoginRequest, LoginResponse> {
    @Override
    Either<Error, LoginResponse> process(LoginRequest input);
}
