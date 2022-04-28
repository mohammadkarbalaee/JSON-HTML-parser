package sbu.cs.parser.json;

import java.util.ArrayList;

public class Driver {
  public static void main(String[] args){
    String jsonString = "{\"name\": \"sahar\", \"age\":20, \"address\": [1,\"yes\",false,2.5,true]}";
    Json json = JsonParser.parse(jsonString);

    System.out.println(json.getKeyValuePairs().getKeys());
    System.out.println(json.getKeyValuePairs().getValues());
    System.out.println();

    System.out.println(json.get("name").getClass());
    System.out.println();

    System.out.println(json.get("age").getClass());
    System.out.println();

    for (Object obj: (ArrayList) json.get("address")) {
      System.out.println(obj.getClass());
    }
  }
}
