package sbu.cs.parser.json;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private static JsonParser jsonParser;

    @BeforeAll
    static void setUp() {
        jsonParser = new JsonParser();
    }

    @Test
    void simpleJson1() {
        String jsonString = "{\"name\": \"sahar\", \"age\":20, \"address\": null}";
    }
}