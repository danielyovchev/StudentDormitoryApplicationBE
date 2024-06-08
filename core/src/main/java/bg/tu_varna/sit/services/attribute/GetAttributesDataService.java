package bg.tu_varna.sit.services.attribute;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Attribute;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.attribute.GetAttributesRequest;
import bg.tu_varna.sit.model.attribute.GetAttributesResponse;
import bg.tu_varna.sit.model.dto.AttributeDTO;
import bg.tu_varna.sit.operation.attribute.GetAttributesOperation;
import bg.tu_varna.sit.repository.AttributeRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class GetAttributesDataService implements GetAttributesOperation {
    private final AttributeRepository attributeRepository;
    @Override
    @Transactional
    public Either<Error, GetAttributesResponse> process(GetAttributesRequest input) {
        return Try.of(() -> {
                    List<Attribute> attributes = attributeRepository.listAll();
                    List<AttributeDTO> mappedAttributes = attributes.stream()
                            .map(attribute -> AttributeDTO.builder()
                                    .id(attribute.getId())
                                    .name(attribute.getName())
                                    .defaultValue(attribute.getDefaultValue())
                                    .description(attribute.getDescription())
                                    .build())
                            .toList();
                    return GetAttributesResponse
                            .builder()
                            .attributes(mappedAttributes)
                            .build();
                })
                .toEither()
                .mapLeft(Throwable -> new InternalError());
    }
}
