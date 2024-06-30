package bg.tu_varna.sit.model.dto;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpouseDTO {
    private String name;
    private String city;
    private String address;
    private String phoneNumber;
}
