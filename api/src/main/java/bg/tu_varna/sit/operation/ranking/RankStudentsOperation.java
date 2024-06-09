package bg.tu_varna.sit.operation.ranking;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.ranking.RankStudentsRequest;
import bg.tu_varna.sit.model.ranking.RankStudentsResponse;
import io.vavr.control.Either;

public interface RankStudentsOperation extends OperationProcessor<RankStudentsRequest, RankStudentsResponse> {
    @Override
    Either<Error, RankStudentsResponse> process(RankStudentsRequest input);
}
