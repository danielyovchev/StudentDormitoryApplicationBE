package bg.tu_varna.sit.rankingEngine.attributes;

import bg.tu_varna.sit.entity.Parent;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Attribute;
import bg.tu_varna.sit.repository.ParentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class ADeathParent implements Attribute<Boolean> {
    private final ParentRepository parentRepository;

    @Override
    public Boolean getAttributeValue(Context context) {
        Student student = context.getStudent();
        Optional<Parent> parent = parentRepository.find("studentId", student.getId()).firstResultOptional();
        return parent.isEmpty();
    }
}
