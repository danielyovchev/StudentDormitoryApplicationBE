package bg.tu_varna.sit.operation.rules;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.rule.UpdateRulesRequest;
import bg.tu_varna.sit.model.rule.UpdateRulesResponse;
import io.vavr.control.Either;

public interface UpdateRulesOperation extends OperationProcessor<UpdateRulesRequest, UpdateRulesResponse> {
    @Override
    Either<Error, UpdateRulesResponse> process(UpdateRulesRequest input);
}
