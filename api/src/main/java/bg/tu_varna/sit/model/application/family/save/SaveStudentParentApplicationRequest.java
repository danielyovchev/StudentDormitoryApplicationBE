package bg.tu_varna.sit.model.application.family.save;

import bg.tu_varna.sit.base.OperationInput;
import bg.tu_varna.sit.model.enums.ParentType;
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
    private String address;
    private ParentType parentType;
    private String phoneNumber;
    private String studentNumber;
}
