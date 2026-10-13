package protocol;

import java.util.Arrays;
import java.util.List;

public class CommandParser {
    public static Cmd parse(String input) {
        if (input == null || input.isBlank()) {
            //TODO: fix exception
            throw new RuntimeException("input can not be empty");
        }
        String[] parts = input.trim().split("\\s+");
        OperationEnum operation = OperationEnum.from(parts[0]);
        List<String> args = Arrays.asList(
                Arrays.copyOfRange(parts, 1, parts.length)
        );
        return new Cmd(operation, args);
    }
}
