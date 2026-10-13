package command;

import protocol.Cmd;
import store.Store;

public class SetCommand implements Command {

    @Override
    public String execute(Cmd cmd) {
        Store.set(cmd.getArgs().get(0), cmd.getArgs().get(1));
        return "OK";
    }
}
