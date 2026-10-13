package command;

import protocol.Cmd;
import store.Store;

public class GetCommand implements Command {

    @Override
    public String execute(Cmd cmd) {
        if (cmd.getArgs().size() != 1) {
            return "ERR wrong number of arguments for GET";
        }
        String val = Store.get(cmd.getArgs().get(0));
        return val == null ? "(nil)" : val;
    }
}
