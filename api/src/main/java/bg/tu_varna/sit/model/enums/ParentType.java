package bg.tu_varna.sit.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParentType {
    MOTHER("mother"),
    FATHER("mother");
    private final String label;

    public static ParentType getByLabel(String label) {
        for (ParentType type : ParentType.values()) {
            if (type.getLabel().equals(label)) {
                return type;
            }
        }
        return null;
    }
}
