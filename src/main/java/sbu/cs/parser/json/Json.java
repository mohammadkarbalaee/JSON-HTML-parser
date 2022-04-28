package sbu.cs.parser.json;

import sbu.cs.parser.json.map.Map;

public class Json implements JsonInterface {
    private final Map keyValuePairs;

    public Json() {
        this.keyValuePairs = new Map();
    }

    @Override
    public String getStringValue(String key) {
        return this.keyValuePairs.get(key).toString();
    }

    public Object get(String key) {
        return this.keyValuePairs.get(key);
    }

//    public Integer getIntegerValue(String key) throws WrongValueTypeRequestedException {
//        String stringValue = this.keyValuePairs.get(key);
//        if (isInt(stringValue)) {
//            return Integer.valueOf(stringValue);
//        } else if (stringValue.equals("null")) {
//            return null;
//        } else {
//            throw
//                new WrongValueTypeRequestedException("The value you requested is not of type integer");
//        }
//    }
//
//    public Boolean getBooleanValue(String key) throws WrongValueTypeRequestedException {
//        String stringValue = this.keyValuePairs.get(key);
//        if (this.isBoolean(stringValue)) {
//            return Boolean.valueOf(stringValue);
//        } else if (stringValue.equals("null")) {
//            return null;
//        } else {
//            throw
//                new WrongValueTypeRequestedException("The value you requested is not of type boolean");
//        }
//    }
//
//    public Map getKeyValuePairs() {
//        return keyValuePairs;
//    }
//
//    public Double getDoubleValue(String key) throws WrongValueTypeRequestedException {
//        String stringValue = this.keyValuePairs.get(key);
//        if (isDouble(stringValue)) {
//            return Double.valueOf(stringValue);
//        } else if (stringValue.equals("null")) {
//            return null;
//        } else {
//            throw
//                new WrongValueTypeRequestedException("The value you requested is not of type Double");
//        }
//    }
//
//    public ArrayList getListValue(String key) throws WrongValueTypeRequestedException {
//        String stringValue = this.keyValuePairs.get(key);
//        if (stringValue.startsWith("[")) {
//            ArrayList valueList = new ArrayList();
//            stringValue = stringValue.replaceAll("[\\[\\]]","");
//            String[] values = stringValue.split(",");
//            for (String value: values) {
//                if (this.isBoolean(value)) {
//                    valueList.add(Boolean.parseBoolean(value));
//                } else if (value.equals("null")) {
//                    valueList.add(null);
//                } else if (this.isInt(value)) {
//                    valueList.add(Integer.parseInt(value));
//                } else if (this.isDouble(value)) {
//                    valueList.add(Double.parseDouble(value));
//                } else {
//                    valueList.add(value);
//                }
//            }
//            return valueList;
//        } else if (stringValue.equals("null")) {
//            return null;
//        } else {
//            throw
//                new WrongValueTypeRequestedException("The value you requested is not a list");
//        }
//    }

    public void add(String key,Object value) {
        this.keyValuePairs.put(key,value);
    }

//    private boolean isInt(String string) {
//        Pattern intPattern = Pattern.compile("[0-9]*");
//        Matcher matcher = intPattern.matcher(string);
//        return matcher.matches();
//    }
//
//    private boolean isDouble(String string) {
//        Pattern intPattern = Pattern.compile("[0-9]+.[0-9]+");
//        Matcher matcher = intPattern.matcher(string);
//        return matcher.matches();
//    }
//
//    private boolean isBoolean(String string) {
//        Pattern intPattern = Pattern.compile("(true|false)");
//        Matcher matcher = intPattern.matcher(string);
//        return matcher.matches();
//    }
}