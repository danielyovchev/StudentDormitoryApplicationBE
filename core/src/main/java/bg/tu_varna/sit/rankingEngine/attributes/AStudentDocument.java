package bg.tu_varna.sit.rankingEngine.attributes;

import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Attribute;
import bg.tu_varna.sit.repository.DocumentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class AStudentDocument implements Attribute<List<Document>> {
    private final DocumentRepository documentRepository;
    @Override
    public List<Document> getAttributeValue(Context context) {
        Student student = context.getStudent();
        List<Document> documents = documentRepository.getStudentVerifiedDocuments(student.getId());
        return documents;
    }
}
