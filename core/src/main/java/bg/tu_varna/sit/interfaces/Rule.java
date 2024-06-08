package bg.tu_varna.sit.interfaces;

import bg.tu_varna.sit.services.context.Context;

public interface Rule {
    Boolean evaluate(Context context);
}
