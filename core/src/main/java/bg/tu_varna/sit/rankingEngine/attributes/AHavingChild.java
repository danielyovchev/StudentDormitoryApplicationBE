package bg.tu_varna.sit.rankingEngine.attributes;

import bg.tu_varna.sit.entity.Child;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Attribute;
import bg.tu_varna.sit.repository.ChildRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class AHavingChild implements Attribute<Boolean> {
    private final ChildRepository childRepository;

    @Override
    public Boolean getAttributeValue(Context context) {
        Student student = context.getStudent();
        Optional<Child> child = childRepository.find("studentId", student.getId()).firstResultOptional();
        return child.isPresent();
    }
}
