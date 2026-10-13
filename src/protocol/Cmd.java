package protocol;

import java.util.List;

public class Cmd {
    private final OperationEnum operation;
    private final List<String> args;

    public Cmd(OperationEnum operation, List<String> args) {
        this.operation = operation;
        this.args = List.copyOf(args);
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public List<String> getArgs() {
        return args;
    }
}