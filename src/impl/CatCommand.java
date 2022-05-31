package impl;

import core.ICommand;
import core.exception.CommandException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.isRegularFile;

public class CatCommand extends Command implements ICommand {

    final static String DIR = System.getProperty("user.dir");

    final static String NAME = "cat";

    final static String KEYS = "En";


    public static void main(String... args) {
        try {
            new CatCommand().executeCommand(args);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return NAME;
    }

    @Override
    public String getLegalKeys(){
        return KEYS;
    }

    @Override
    public void execute(Map<String, ArrayList<String>> parameterMap) {
        boolean modifyNumber = parameterMap.get("key").contains("n");
        boolean modifyEnd = parameterMap.get("key").contains("E");


        if ( parameterMap.get("file").isEmpty()) {
            System.out.println("The list of files is empty.");
        } else {
            for (String fileName : parameterMap.get("file")) {

                Path filePath = Path.of(DIR + System.getProperty("file.separator") + fileName);
                if (!isRegularFile(filePath)) {
                    System.out.println("There is no file " + filePath);
                } else

                    try {
                        List<String> lines = Files.readAllLines(filePath, UTF_8);
                        int number = 1;
                        for (String line : lines) {
                            System.out.println(modify(line, modifyNumber, number, modifyEnd));
                            number++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        }
    }


    private String modify(String line, boolean numberFlag, int number, boolean endFlag) {
        if (numberFlag) {
            line = number + " " + line;
        }
        if (endFlag) {
            line = line + " $";
        }
        return line;
    }


}
