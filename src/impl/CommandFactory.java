package impl;

import core.ICommand;
import core.ICommandFactory;

import java.util.Collection;
import java.util.List;

public class CommandFactory implements ICommandFactory {
    private static final Collection<ICommand> commands = List.of(new CatCommand(), new LsCommand(), new JDBCCommand());

    @Override
    public Collection<ICommand> createCommand() {

        return commands;
    }

}
