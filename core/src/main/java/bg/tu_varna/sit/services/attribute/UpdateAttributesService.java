package bg.tu_varna.sit.services.attribute;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Attribute;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.attribute.UpdateAttributesRequest;
import bg.tu_varna.sit.model.attribute.UpdateAttributesResponse;
import bg.tu_varna.sit.model.dto.AttributeDTO;
import bg.tu_varna.sit.operation.attribute.UpdateAttributesOperation;
import bg.tu_varna.sit.repository.AttributeRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class UpdateAttributesService implements UpdateAttributesOperation {
    private final AttributeRepository attributeRepository;
    @Override
    @Transactional
    public Either<Error, UpdateAttributesResponse> process(UpdateAttributesRequest input) {
        return Try.of(() -> {
                    List<AttributeDTO> attributeDTOS = input.getAttributes();
                    attributeDTOS.forEach(attributeDTO -> {
                        Optional<Attribute> attribute = Optional.ofNullable(attributeRepository.findById(attributeDTO.getId()));
                        if (attribute.isPresent()) {
                            Attribute attributeEntity = attribute.get();
                            attributeEntity.setDefaultValue(attributeDTO.getDefaultValue());
                            attributeRepository.persist(attributeEntity);
                        }
                    });
                    return UpdateAttributesResponse.builder()
                            .message("Successful update")
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
