package sbu.cs.parser.json;

import sbu.cs.parser.json.map.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Json implements JsonInterface {
    private Pattern pattern;
    private Matcher matcher;
    private final Map pairs;

    public Json() {
        this.pairs = new Map();
    }

    @Override
    public String getStringValue(String key) {
        return this.pairs.get(key);
    }

    public Integer getIntegerValue(String key) throws WrongValueTypeRequestedException {
        String stringValue = this.pairs.get(key);
        pattern = Pattern.compile("[0-9]*");
        matcher = pattern.matcher(stringValue);
        if (matcher.matches()) {
            return Integer.valueOf(stringValue);
        } else if (stringValue.equals("null")) {
            return null;
        } else {
            throw
                new WrongValueTypeRequestedException("The value you requested is not of type integer");
        }
    }

    public Boolean getBooleanValue(String key) throws WrongValueTypeRequestedException {
        String stringValue = this.pairs.get(key);
        if (stringValue.equals("true") ||
            stringValue.equals("false")) {
            return Boolean.valueOf(stringValue);
        } else if (stringValue.equals("null")) {
            return null;
        } else {
            throw
                new WrongValueTypeRequestedException("The value you requested is not of type boolean");
        }
    }

    public Map getPairs() {
        return pairs;
    }

    public Double getDoubleValue(String key) throws WrongValueTypeRequestedException {
        String stringValue = this.pairs.get(key);
        pattern = Pattern.compile("[0-9]*\\\\.?[0-9]+$");
        matcher = pattern.matcher(stringValue);
        if (matcher.matches()) {
            return Double.valueOf(stringValue);
        } else if (stringValue.equals("null")) {
            return null;
        } else {
            throw
                new WrongValueTypeRequestedException("The value you requested is not of type Double");
        }
    }

    public void add(String key,String value) {
        this.pairs.put(key,value);
    }
}