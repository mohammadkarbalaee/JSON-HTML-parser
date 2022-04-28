package sbu.cs.parser.json;

public class Driver {
  public static void main(String[] args) throws WrongValueTypeRequestedException {
    String jsonString = "{\"name\": \"sahar\", \"age\":20, \"address\": [1,\"yes\",false,2.5,true]}";
    Json json = JsonParser.parse(jsonString);
//    System.out.println(json.getPairs().getKeys());
//    System.out.println(json.getPairs().getValues());
  }
}
