package bg.tu_varna.sit.model.application.family.save;

import bg.tu_varna.sit.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveStudentChildDataRequest implements OperationInput {
    private String name;
    private LocalDate birthDate;
}
