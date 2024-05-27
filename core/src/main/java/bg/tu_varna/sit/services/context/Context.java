package bg.tu_varna.sit.services.context;

import bg.tu_varna.sit.entity.Student;
import lombok.*;

@Getter
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Context {
    private Student student;
}
