package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.enums.DocumentEnum;
import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.attributes.ADeathDocument;
import bg.tu_varna.sit.rankingEngine.attributes.AStudentDocument;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
@RuleQualifier("Rule003")
public class Rule003 implements Rule {
    private final AStudentDocument studentDocuments;
    private final ADeathDocument deathDocuments;
    @Override
    public Boolean evaluate(Context context) {
        List<Document> documents = studentDocuments.getAttributeValue(context);
        DocumentEnum documentType = deathDocuments.getAttributeValue(context);
        return documents.stream()
                .anyMatch(doc -> doc.getDocumentEnum().equals(documentType));
    }
}
