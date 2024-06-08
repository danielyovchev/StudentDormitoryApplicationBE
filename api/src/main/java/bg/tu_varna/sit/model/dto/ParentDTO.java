package bg.tu_varna.sit.model.dto;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParentDTO {
    private String name;
    private String city;
    private String street;
    private Integer streetNumber;
    private String phoneNumber;
}
