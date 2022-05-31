package core;

import core.exception.CommandException;
import core.exception.UndefinedKeyException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Core {
    private static Map<String, ICommand> commandMapping ;

    public Core(ICommandFactory cmdFactory) {
        commandMapping = cmdFactory.createCommand().stream().collect(Collectors.toMap(ICommand::getName, Function.identity()));
    }

    public void StartCmd(String[] args) {

        if (args.length == 0) {
            System.out.println("No parameters");
            return;
        }

        ICommand command = commandMapping.get(args[0]);

        if (command == null) System.out.println("Command \""+args[0]+"\" is not defined. All commands: " + commandMapping.keySet());

        try {
            assert command != null;
            command.executeCommand(Arrays.copyOfRange(args, 1, args.length));
        } catch (UndefinedKeyException e) {
            System.out.println(e.getMessage() + "There is no key like " + String.join(",", e.getKeyList()));
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }

    }


}
