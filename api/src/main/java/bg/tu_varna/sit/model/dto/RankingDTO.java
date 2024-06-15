package bg.tu_varna.sit.model.dto;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankingDTO {
    private String studentName;
    private Integer score;
}
