package bg.tu_varna.sit.services.rules;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.RuleEntity;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.dto.RuleDTO;
import bg.tu_varna.sit.model.rule.UpdateRulesRequest;
import bg.tu_varna.sit.model.rule.UpdateRulesResponse;
import bg.tu_varna.sit.operation.rules.UpdateRulesOperation;
import bg.tu_varna.sit.repository.RuleRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class UpdateRulesService implements UpdateRulesOperation {
    private final RuleRepository ruleRepository;

    @Override
    @Transactional
    public Either<Error, UpdateRulesResponse> process(UpdateRulesRequest input) {
        return Try.of(() -> {
                    List<RuleDTO> ruleDTOS = input.getRules();
                    ruleDTOS.forEach(ruleDTO -> {
                        Optional<RuleEntity> rule = Optional.ofNullable(ruleRepository.findById(ruleDTO.getId()));
                        if (rule.isPresent()) {
                            RuleEntity ruleEntity = rule.get();
                            ruleEntity.setDefaultScore(ruleDTO.getDefaultScore());
                            ruleEntity.setIsActive(ruleDTO.getIsActive());
                            ruleRepository.persist(ruleEntity);
                        }
                    });
                    return UpdateRulesResponse.builder()
                            .message("Rules are updated")
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
