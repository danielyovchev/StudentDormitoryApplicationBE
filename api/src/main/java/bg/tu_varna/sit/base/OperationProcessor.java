package bg.tu_varna.sit.base;

import io.vavr.control.Either;

public interface OperationProcessor<I extends OperationInput, R extends OperationResponse> {
    Either<Error, R> process();
}
