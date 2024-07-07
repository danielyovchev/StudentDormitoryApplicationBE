package bg.tu_varna.sit.rankingEngine.rules;

import bg.tu_varna.sit.entity.Document;
import bg.tu_varna.sit.enums.DocumentEnum;
import bg.tu_varna.sit.rankingEngine.annotations.RuleQualifier;
import bg.tu_varna.sit.rankingEngine.attributes.AHavingChild;
import bg.tu_varna.sit.rankingEngine.attributes.ANoSpouse;
import bg.tu_varna.sit.rankingEngine.attributes.AStudentDocument;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Rule;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
@RuleQualifier("Rule002")
public class Rule002 implements Rule {
    private final AHavingChild aHavingChild;
    private final ANoSpouse aNoSpouse;
    private final AStudentDocument studentDocument;
    @Override
    public Boolean evaluate(Context context) {
        Boolean havingChildAttribute = aHavingChild.getAttributeValue(context);
        Boolean noSpouseAttribute = aNoSpouse.getAttributeValue(context);
        List<Document> documents = studentDocument.getAttributeValue(context);
        Boolean validDocuments = documents.stream()
                .map(Document::getDocumentEnum)
                .anyMatch(d -> d.equals(DocumentEnum.CHILD));
        return havingChildAttribute && noSpouseAttribute && validDocuments;
    }
}
