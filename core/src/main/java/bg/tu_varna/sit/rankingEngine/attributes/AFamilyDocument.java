package bg.tu_varna.sit.rankingEngine.attributes;

import bg.tu_varna.sit.enums.DocumentEnum;
import bg.tu_varna.sit.rankingEngine.context.Context;
import bg.tu_varna.sit.rankingEngine.interfaces.Attribute;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AFamilyDocument implements Attribute<DocumentEnum> {
    @Override
    public DocumentEnum getAttributeValue(Context context) {
        return DocumentEnum.LARGEFAMILY;
    }
}
