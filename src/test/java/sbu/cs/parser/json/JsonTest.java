package sbu.cs.parser.json;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    @Test
    void simpleJson1() {
        String jsonString = "{\"name\": \"sahar\", \"age\":20, \"address\": null}";
        Json json = JsonParser.parse(jsonString);
        assert json != null;
        assertEquals("sahar", json.getStringValue("name"));
        assertEquals("20", json.getStringValue("age"));
        assertEquals("null", json.getStringValue("address"));
    }

    @Test
    void arrayJson() {
        String jsonString = "{\"key1\": [1, 3, 2], \"key2\": true}";
        Json json = JsonParser.parse(jsonString);
        assert json != null;
        assertEquals("[1, 3, 2]", json.getStringValue("key1"));
        assertEquals("true", json.getStringValue("key2"));
    }

    @Test
    void simpleTest2() {
        String jsonString = "{\n\t\"name\": \"sahar\",\"age\":20,\n\t\"address\":           null\n}\n";
        Json json = JsonParser.parse(jsonString);
        assert json != null;
        assertEquals("sahar", json.getStringValue("name"));
        assertEquals("20", json.getStringValue("age"));
        assertEquals("null", json.getStringValue("address"));
    }

    @Test
    void jsonWithType() {
        String jsonString = "{\"name\": \"sahar\", \"age\":20, \"arr\": [1,\"yes\",false,2.5,true]}";
        Json json = JsonParser.parse(jsonString);
        assert json != null;
        assertEquals(20,(Integer) json.get("age"));
        assertEquals("sahar",(String) json.get("name"));
        ArrayList list = (ArrayList) json.get("arr");
        assertEquals(5,list.size());
    }
}