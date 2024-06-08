package bg.tu_varna.sit.interfaces;

import bg.tu_varna.sit.services.context.Context;

public interface Attribute<T> {
    T getAttributeValue(Context context);
}
