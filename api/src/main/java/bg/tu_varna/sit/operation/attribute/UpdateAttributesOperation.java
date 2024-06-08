package bg.tu_varna.sit.operation.attribute;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.attribute.UpdateAttributesRequest;
import bg.tu_varna.sit.model.attribute.UpdateAttributesResponse;
import io.vavr.control.Either;

public interface UpdateAttributesOperation extends OperationProcessor<UpdateAttributesRequest, UpdateAttributesResponse> {
    @Override
    Either<Error, UpdateAttributesResponse> process(UpdateAttributesRequest input);
}
