package bg.tu_varna.sit.operation.rules;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.rule.GetRulesRequest;
import bg.tu_varna.sit.model.rule.GetRulesResponse;
import io.vavr.control.Either;

public interface GetRulesOperation extends OperationProcessor<GetRulesRequest, GetRulesResponse> {
    @Override
    Either<Error, GetRulesResponse> process(GetRulesRequest input);
}
