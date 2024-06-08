package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.base.OperationProcessor;
import bg.tu_varna.sit.entity.RuleEntity;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.dto.RuleDTO;
import bg.tu_varna.sit.model.rule.GetRulesRequest;
import bg.tu_varna.sit.model.rule.GetRulesResponse;
import bg.tu_varna.sit.operation.rules.GetRulesOperation;
import bg.tu_varna.sit.repository.RuleRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class GetRulesService implements GetRulesOperation {
    private final RuleRepository ruleRepository;

    @Override
    @Transactional
    public Either<Error, GetRulesResponse> process(GetRulesRequest input) {
        return Try.of(() -> {
                    List<RuleEntity> ruleEntities = ruleRepository.listAll();
                    List<RuleDTO> ruleDTOS = ruleEntities.stream()
                            .map(rule -> RuleDTO.builder()
                                    .id(rule.getId())
                                    .name(rule.getName())
                                    .description(rule.getDescription())
                                    .defaultScore(rule.getDefaultScore())
                                    .isActive(rule.getIsActive())
                                    .build())
                            .toList();
                    return GetRulesResponse.builder()
                            .rules(ruleDTOS)
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
