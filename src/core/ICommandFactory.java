package core;

import java.util.Collection;

public interface ICommandFactory {

    Collection<ICommand> createCommand();

}
