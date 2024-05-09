package bg.tu_varna.sit.model.application.family.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveStudentSiblingDataRequest {
    private String name;
    private Boolean isStudying;
}
