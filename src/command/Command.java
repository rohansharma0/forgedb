package command;

import protocol.Cmd;

public interface Command {
    String execute(Cmd cmd);
}
