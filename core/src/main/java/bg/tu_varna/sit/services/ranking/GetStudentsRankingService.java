package bg.tu_varna.sit.services.ranking;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.StudentScore;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.dto.RankingDTO;
import bg.tu_varna.sit.model.ranking.GetStudentRankingRequest;
import bg.tu_varna.sit.model.ranking.GetStudentRankingResponse;
import bg.tu_varna.sit.operation.ranking.GetStudentsRankingOperation;
import bg.tu_varna.sit.repository.StudentScoreRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class GetStudentsRankingService implements GetStudentsRankingOperation {
    private final StudentScoreRepository studentScoreRepository;

    @Override
    public Either<Error, GetStudentRankingResponse> process(GetStudentRankingRequest input) {
        return Try.of(() -> {
                    List<StudentScore> studentScores = studentScoreRepository.listAll();
                    List<RankingDTO> rankings = studentScores.stream()
                            .map(el -> RankingDTO.builder()
                                    .studentName(el.getStudent().getName())
                                    .score(el.getScore())
                                    .build())
                            .toList();
                    return GetStudentRankingResponse.builder()
                            .ranking(rankings)
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
