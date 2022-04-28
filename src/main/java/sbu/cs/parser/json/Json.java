package sbu.cs.parser.json;

import sbu.cs.parser.json.map.Map;

public class Json implements JsonInterface {
    private final Map keyValuePairs;

    public Json() {
        this.keyValuePairs = new Map();
    }

    @Override
    public String getStringValue(String key) {
        if (this.keyValuePairs.get(key) == null) {
            return "null";
        }
        return this.keyValuePairs.get(key).toString();
    }

    public Object get(String key) {
        return this.keyValuePairs.get(key);
    }

    public Map getKeyValuePairs() {
        return keyValuePairs;
    }

    public void add(String key,Object value) {
        this.keyValuePairs.put(key,value);
    }

}