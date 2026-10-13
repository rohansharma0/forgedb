package executor;

import command.Command;
import command.GetCommand;
import command.PingCommand;
import command.SetCommand;
import protocol.Cmd;
import protocol.OperationEnum;

public class CommandExecutor {

    public static String execute(Cmd cmd) {
        Command command = switch (cmd.getOperation()) {
            case OperationEnum.PING -> new PingCommand();
            case OperationEnum.GET -> new GetCommand();
            case OperationEnum.SET -> new SetCommand();
        };
        return command.execute(cmd);
    }
}
