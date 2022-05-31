package impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parameters {

    static Map<String, ArrayList<String>> getParameterMap(String[] args) {
        ArrayList<String> optionKeyList = new ArrayList<>();
        ArrayList<String> fileNameList = new ArrayList<>();
            for (String arg : args) {
                if (arg.charAt(0) == '-') {
                    String keys = arg.substring(1);
                    if (keys.length() == 1) {
                        optionKeyList.add(keys);
                    } else {
                        for (char c : keys.toCharArray()) {
                            optionKeyList.add(String.valueOf(c));
                        }
                    }
                } else {
                    fileNameList.add(arg);
                }
            }
        Map<String, ArrayList<String>> parameterMap = new HashMap<>();
        parameterMap.put("key", optionKeyList);
        parameterMap.put("file", fileNameList);
        return parameterMap;
    }
}
