package bg.tu_varna.sit.services.ranking;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.RuleEntity;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.entity.StudentScore;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import bg.tu_varna.sit.model.ranking.RankStudentsRequest;
import bg.tu_varna.sit.model.ranking.RankStudentsResponse;
import bg.tu_varna.sit.operation.ranking.RankStudentsOperation;
import bg.tu_varna.sit.repository.RuleRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import bg.tu_varna.sit.repository.StudentScoreRepository;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.services.rules.RuleFactory;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class RankStudentService implements RankStudentsOperation {
    private final RuleRepository ruleRepository;
    private final StudentRepository studentRepository;
    private final StudentScoreRepository studentScoreRepository;
    private final RuleFactory ruleFactory;

    @Override
    @Transactional
    public Either<Error, RankStudentsResponse> process(RankStudentsRequest input) {
        return Try.of(() -> {
                    List<RuleEntity> activeRuleEntities = ruleRepository.findAllActiveRules();
                    List<Student> students = studentRepository.listAll();
                    students.forEach(student -> {
                        int score = 0;
                        final Context context = buildContext(student);
                        for (RuleEntity ruleEntity : activeRuleEntities) {
                            Rule rule = ruleFactory.mapRule(ruleEntity.getName());
                            if (rule.evaluate(context)) {
                                score += ruleEntity.getDefaultScore();
                                StudentScore studentScore = new StudentScore();
                                studentScore.setScore(score);
                                studentScore.setStudent(student);
                                studentScoreRepository.persist(studentScore);
                            }
                        }
                    });
                    return RankStudentsResponse.builder()
                            .message("Ranking done")
                            .build();
                }).toEither()
                .mapLeft(Throwable -> new InternalError());
    }


    private Context buildContext(Student student) {
        return Context.builder()
                .student(student)
                .build();
    }
}
