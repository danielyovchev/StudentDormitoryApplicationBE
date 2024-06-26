package bg.tu_varna.sit.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankingDTO {
    private UUID id;
    private String studentName;
    private Integer score;
}
