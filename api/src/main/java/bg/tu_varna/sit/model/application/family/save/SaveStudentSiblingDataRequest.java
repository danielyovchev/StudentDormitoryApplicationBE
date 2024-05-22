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
public class SaveStudentSiblingDataRequest implements OperationInput {
    private String name;
    private Boolean isStudying;
    private String studentPersonalNumber;
}
