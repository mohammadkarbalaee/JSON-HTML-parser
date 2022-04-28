package sbu.cs.parser.json;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    public static Json parse(String jsonString) {
      Json builtJson = new Json();
      jsonString = jsonString.replaceAll(",(?=((?!\\[).)*?\\])","@");
      String[] keyValuePairs = jsonString.split(",");
      for (int i = 0; i < keyValuePairs.length; i++) {
        keyValuePairs[i] = keyValuePairs[i].replaceAll("@(?=((?!\\[).)*?\\])",",");
      }
      for (String pair: keyValuePairs) {
        pair = pair.replaceAll("[{}]","");
        String[] parsedPair = pair.split(":");
        parsedPair[0] = parsedPair[0].trim().replaceAll("\"","");
        parsedPair[1] = parsedPair[1].trim().replaceAll("\"","");
        if (isBoolean(parsedPair[1])) {
            builtJson.add(parsedPair[0],Boolean.parseBoolean(parsedPair[1]));
        } else if (parsedPair[1].equals("null")) {
            builtJson.add(parsedPair[0],null);
        } else if (isInt(parsedPair[1])) {
            builtJson.add(parsedPair[0],Integer.parseInt(parsedPair[1]));
        } else if (isDouble(parsedPair[1])) {
            builtJson.add(parsedPair[0],Double.parseDouble(parsedPair[1]));
        } else if(parsedPair[1].startsWith("[")) {
          builtJson.add(parsedPair[0],getList(parsedPair[1]));
        } else {
            builtJson.add(parsedPair[0],parsedPair[1]);
        }
      }
      return builtJson;
    }

  private static ArrayList getList(String listValue) {
    ArrayList valueList = new ArrayList();
    listValue = listValue.replaceAll("[\\[\\]]","");
    String[] values = listValue.split(",");
    for (String value: values) {
        if (isBoolean(value)) {
            valueList.add(Boolean.parseBoolean(value));
        } else if (value.equals("null")) {
            valueList.add(null);
        } else if (isInt(value)) {
            valueList.add(Integer.parseInt(value));
        } else if (isDouble(value)) {
            valueList.add(Double.parseDouble(value));
        } else {
            valueList.add(value);
        }
    }
    return valueList;
  }

  private static boolean isInt(String string) {
        Pattern intPattern = Pattern.compile("[0-9]*");
        Matcher matcher = intPattern.matcher(string);
        return matcher.matches();
    }

    private static boolean isDouble(String string) {
        Pattern intPattern = Pattern.compile("[0-9]+.[0-9]+");
        Matcher matcher = intPattern.matcher(string);
        return matcher.matches();
    }

    private static boolean isBoolean(String string) {
        Pattern intPattern = Pattern.compile("(true|false)");
        Matcher matcher = intPattern.matcher(string);
        return matcher.matches();
    }
}