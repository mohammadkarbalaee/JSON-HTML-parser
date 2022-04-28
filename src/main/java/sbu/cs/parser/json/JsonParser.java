package sbu.cs.parser.json;

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
        String[] parsed = pair.split(":");
        builtJson.add(parsed[0].trim().replaceAll("\"","")
            ,parsed[1].trim().replaceAll("\"",""));
      }
      return builtJson;
    }
}