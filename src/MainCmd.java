import core.Core;
import impl.CommandFactory;

public class MainCmd {
    public static void main(String[] args){
        new Core(new CommandFactory()).StartCmd(args);

    }
}
