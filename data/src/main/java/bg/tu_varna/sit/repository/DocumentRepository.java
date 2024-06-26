package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Document;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DocumentRepository implements PanacheRepositoryBase<Document, Long> {
    public List<Document> getUnverifiedDocuments() {
        return list("validated=false");
    }

    public List<Document> getStudentVerifiedDocuments(UUID studentId) {
        return list("validated=true and student.id = ?1", studentId);
    }

    public List<Document> getStudentUnverifiedDocuments(UUID studentId) {
        return list("validated=false and student.id = ?1", studentId);
    }
}
