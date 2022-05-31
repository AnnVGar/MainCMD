package impl;

import core.exception.CommandException;
import core.ICommand;

import java.io.File;
import java.util.*;

public class LsCommand extends Command implements ICommand {


    final static String DIR = System.getProperty("user.dir");

    final static String NAME= "ls";

    final static String KEYS= "Rr";



    public static void main(String... args) {
        try {
            new LsCommand().executeCommand(args);
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


        File currentDir = new File(DIR);

        boolean modifyDesc = parameterMap.get("key").contains("r");
        boolean modifyTree = parameterMap.get("key").contains("R");

        ls(currentDir, modifyDesc, modifyTree, "");


    }

    private void ls(File dir, boolean modifyDesc, boolean modifyTree, String indent) {
        File[] files = dir.listFiles();
        if (modifyDesc) {
            sortByNameDesc(files);
        } else {
            sortByName(files);
        }
        for (File file : files) {
            System.out.println(indent + file.getName());
            if (!file.isDirectory() || !modifyTree)
                continue;

            String indentOld = indent;
            indent += "  ";
            ls(file, modifyDesc, true, indent);
            indent = indentOld;
        }
    }

    public static void sortByName(File[] files) {
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public static void sortByNameDesc(File[] files) {
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File o1, File o2) {
                return -1 * o1.getName().compareTo(o2.getName());
            }
        });
    }


}
