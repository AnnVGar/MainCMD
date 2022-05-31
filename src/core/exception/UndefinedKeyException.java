package core.exception;

import java.util.List;

public class UndefinedKeyException extends CommandException {
    List keyList;

    public UndefinedKeyException(String message, List<String> keyParam) {
        super(message);
        this.keyList = keyParam;
    }

    public List getKeyList() {
        return keyList;
    }
}