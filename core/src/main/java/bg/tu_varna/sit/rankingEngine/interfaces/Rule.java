package bg.tu_varna.sit.rankingEngine.interfaces;

import bg.tu_varna.sit.rankingEngine.context.Context;

public interface Rule {
    Boolean evaluate(Context context);
}
