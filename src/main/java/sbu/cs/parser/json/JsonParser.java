package sbu.cs.parser.json;

public class JsonParser {
    public static Json parse(String jsonString) {
      Json builtJson = new Json();
      String[] keyValuePairs = jsonString.split(",");

      for (String pair: keyValuePairs) {
        pair = pair.replaceAll("[{}]","");
        String[] parsed = pair.split(":");
        builtJson.add(parsed[0].trim(),parsed[1].trim());
      }
      return builtJson;
    }
}