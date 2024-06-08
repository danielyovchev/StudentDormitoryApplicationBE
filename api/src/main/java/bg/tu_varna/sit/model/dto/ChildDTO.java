package bg.tu_varna.sit.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChildDTO {
    private String name;
    private LocalDate birthDate;
}
