package bg.tu_varna.sit.model.application;

import bg.tu_varna.sit.base.OperationInput;
import bg.tu_varna.sit.model.dto.ApplicationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveApplicationRequest implements OperationInput {
    private String studentNumber;
}