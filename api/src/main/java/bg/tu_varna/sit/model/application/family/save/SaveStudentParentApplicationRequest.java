package bg.tu_varna.sit.model.application.family.save;

import bg.tu_varna.sit.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveStudentParentApplicationRequest implements OperationInput {
    private String name;
    private String city;
    private String street;
    private Integer streetNumber;
    private Integer buildingNumber;
    private Integer entrance;
    private Integer apartment;
    private String phoneNumber;
    private String studentPersonalNumber;
}
