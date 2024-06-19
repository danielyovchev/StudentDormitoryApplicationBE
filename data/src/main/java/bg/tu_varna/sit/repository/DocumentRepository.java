package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Document;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DocumentRepository implements PanacheRepositoryBase<Document, Long> {
    public List<Document> getUnverifiedDocuments() {
        return list("validated=false");
    }
}
