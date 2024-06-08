package bg.tu_varna.sit.operation.attribute;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.attribute.GetAttributesRequest;
import bg.tu_varna.sit.model.attribute.GetAttributesResponse;
import io.vavr.control.Either;

public interface GetAttributesOperation extends OperationProcessor<GetAttributesRequest, GetAttributesResponse> {
    @Override
    Either<Error, GetAttributesResponse> process(GetAttributesRequest input);
}
