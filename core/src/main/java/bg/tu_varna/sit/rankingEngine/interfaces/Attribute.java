package bg.tu_varna.sit.rankingEngine.interfaces;

import bg.tu_varna.sit.rankingEngine.context.Context;

public interface Attribute<T> {
    T getAttributeValue(Context context);
}
