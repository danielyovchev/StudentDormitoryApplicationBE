package bg.tu_varna.sit.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleDTO {
    private Long id;
    private String name;
    private String description;
    private Integer defaultScore;
    private Boolean isActive;
}
