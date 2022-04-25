package sbu.cs.parser.json;

import sbu.cs.parser.json.map.Map;
import sbu.cs.parser.json.map.Value;

public class Json implements JsonInterface {

    private Map<Value> pairs;

    public Json() {
        this.pairs = new Map<>();
    }

    @Override
    public String getStringValue(String key) {
        return null;
    }

    public Value get(String key) {
        return null;
    }

    public void add(String key,Value value) {
        this.pairs.put(key,value);
    }
}