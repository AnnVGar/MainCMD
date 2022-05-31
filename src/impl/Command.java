package impl;

import core.ICommand;
import core.exception.CommandException;
import core.exception.UndefinedKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract class Command implements ICommand {


    @Override
    public void executeCommand(String[] args) throws CommandException {

        Map<String, ArrayList<String>> parameterMap = Parameters.getParameterMap(args);

        List<String> undefinedKey = getKeyUndefined(parameterMap.get("key"), this.getLegalKeys());
        if (!undefinedKey.isEmpty()) {
            throw new UndefinedKeyException("", undefinedKey);
        }
        execute(parameterMap);

    }

    private List<String> getKeyUndefined(List<String> parameterList, String legalKey) {

        ArrayList<String> keyList = (ArrayList<String>) parameterList;
        ArrayList<String> undefinedKey = new ArrayList<>();
        for (String key : keyList) {
            if (!legalKey.contains(key)) {
                undefinedKey.add(key);
            }
        }
        return undefinedKey;

    }



    abstract String getLegalKeys();

    abstract void execute(Map<String, ArrayList<String>> parameterMap);

}
