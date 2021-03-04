package sbu.cs.parser.json;

import java.util.HashMap;
import java.util.Map;

public class Json implements JsonInterface {

    Map<String, String> entries = new HashMap<>();

    @Override
    public String getStringValue(String key) {
        this.entries.get(key);
        // TODO implement this
        return null;
    }
}
