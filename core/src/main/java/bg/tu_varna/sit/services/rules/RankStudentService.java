package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Attribute;
import bg.tu_varna.sit.entity.Rule;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.ranking.RankStudentsRequest;
import bg.tu_varna.sit.model.ranking.RankStudentsResponse;
import bg.tu_varna.sit.operation.rules.RankStudentsOperation;
import bg.tu_varna.sit.repository.AttributeRepository;
import bg.tu_varna.sit.repository.RuleRepository;
import bg.tu_varna.sit.repository.StudentRepository;
import bg.tu_varna.sit.services.context.Context;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class RankStudentService implements RankStudentsOperation {
    private final RuleRepository ruleRepository;
    private final StudentRepository studentRepository;
    private final AttributeRepository attributeRepository;
    @Override
    public Either<Error, RankStudentsResponse> process(RankStudentsRequest input) {
        return Try.of(() -> {
                    List<Rule> activeRules = ruleRepository.findAllActiveRules();
                    List<Student> students = studentRepository.listAll();
                    students.forEach(student -> {
                        final Context context = buildContext(student);
                    });
                    return RankStudentsResponse.builder()
                            .message("Ranked")
                            .build();
                }).toEither()
                .mapLeft(Throwable -> new InternalError());
    }

    private void calculateScore(Context context, List<Rule> activeRules) {

    }

    private void computeAttributes(Context context) {
        List<Attribute> attributeList = attributeRepository.listAll();
    }

    private Context buildContext(Student student) {
        return Context.builder()
                .student(student)
                .build();
    }
}
