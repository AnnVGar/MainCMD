package core;

import core.exception.CommandException;

public interface ICommand {

    String getName();
    void executeCommand(String[] args) throws CommandException;
}
