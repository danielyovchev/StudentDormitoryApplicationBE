package bg.tu_varna.sit.operation.ranking;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.model.ranking.GetStudentRankingRequest;
import bg.tu_varna.sit.model.ranking.GetStudentRankingResponse;
import io.vavr.control.Either;

public interface GetStudentsRankingOperation extends OperationProcessor<GetStudentRankingRequest, GetStudentRankingResponse> {
    @Override
    Either<Error, GetStudentRankingResponse> process(GetStudentRankingRequest input);
}
