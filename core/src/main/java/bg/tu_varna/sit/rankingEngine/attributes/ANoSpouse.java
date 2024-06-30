package bg.tu_varna.sit.rankingEngine.attributes;

import bg.tu_varna.sit.entity.Spouse;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Attribute;
import bg.tu_varna.sit.repository.SpouseRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class ANoSpouse implements Attribute<Boolean> {
    private final SpouseRepository spouseRepository;

    @Override
    public Boolean getAttributeValue(Context context) {
        Student student = context.getStudent();
        Optional<Spouse> spouse = spouseRepository.find("student.id", student.getId()).firstResultOptional();
        return spouse.isEmpty();
    }
}
