package sbu.cs.parser.json;

public class Driver {
  public static void main(String[] args) throws WrongValueTypeRequestedException {
    String jsonString = "{\"name\": \"sahar\", \"age\":20, \"address\": null}";
    Json json = JsonParser.parse(jsonString);
    System.out.println(json.getPairs().getKeys());
    System.out.println(json.getPairs().getValues());
    System.out.println(json.getIntegerValue("age"));
  }
}
