package command;

import protocol.Cmd;

public class PingCommand implements Command {

    @Override
    public String execute(Cmd cmd) {
        if(cmd.getArgs().isEmpty()) return "PONG";
        return String.join(" ", cmd.getArgs());
    }
}
