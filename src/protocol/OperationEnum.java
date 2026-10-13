package protocol;

import java.util.Locale;

public enum OperationEnum {
    PING,
    SET,
    GET;

    public static OperationEnum from(String value) {
        try {
            return OperationEnum.valueOf(
                    value.toUpperCase(Locale.ROOT)
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unknown command: " + value
            );
        }
    }
}