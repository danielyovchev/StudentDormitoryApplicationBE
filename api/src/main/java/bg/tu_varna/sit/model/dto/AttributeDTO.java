package bg.tu_varna.sit.model.dto;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeDTO {
    private Long id;
    private String name;
    private String description;
    private String defaultValue;
}
