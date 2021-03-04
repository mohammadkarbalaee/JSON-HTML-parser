package sbu.cs.parser.html;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    private static final String sampleHTML1 = "<html>\n" +
            "<body>\n" +
            "\n" +
            "<p>This text is normal.</p>\n" +
            "\n" +
            "<p><b>This text is bold.</b></p>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
    private static final String sampleHTML2 = "<html>\n" +
            "<body>\n" +
            "\n" +
            "<h2>Width and Height Attributes</h2>\n" +
            "\n" +
            "<p>The width and height attributes of the img tag, defines the width and height of the image:</p>\n" +
            "\n" +
            "<img src=\"img_girl.jpg\" width=\"500\" height=\"600\">\n" +
            "\n" +
            "</body>\n" +
            "</html>";
    private static final String sampleHTML3 = "<html>\n" +
            "<body>\n" +
            "\n" +
            "<h1>The input formaction attribute</h1>\n" +
            "\n" +
            "<p>The formaction attribute specifies the URL of a file that will process the input when the form is submitted.</p>\n" +
            "\n" +
            "<form action=\"/action_page.php\">\n" +
            "  <label for=\"fname\">First name:</label>\n" +
            "  <input type=\"text\" id=\"fname\" name=\"fname\"><br><br>\n" +
            "  <label for=\"lname\">Last name:</label>\n" +
            "  <input type=\"text\" id=\"lname\" name=\"lname\"><br><br>\n" +
            "  <input type=\"submit\" value=\"Submit\">\n" +
            "  <input type=\"submit\" formaction=\"/action_page2.php\" value=\"Submit as Admin\">\n" +
            "</form>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    @Test
    void getStringValue1() {
        Node parse = HTMLParser.parse(sampleHTML1);
        Node node = parse.getChildren().get(0).getChildren().get(1);
        assertEquals("<b>This text is bold.</b>", node.getStringInside());
        assertEquals("This text is bold.", node.getChildren().get(0).getStringInside());
    }

    @Test
    void getStringValue2() {
        Node parse = HTMLParser.parse(sampleHTML2);
        String stringInside = parse.getChildren().get(0).getChildren().get(2).getStringInside();
        assertNull(stringInside);
        stringInside = parse.getChildren().get(0).getChildren().get(0).getStringInside();
        assertEquals("Width and Height Attributes", stringInside);
    }

    @Test
    void getAttributeValue2() {
        Node parse = HTMLParser.parse(sampleHTML2);
        Node node = parse.getChildren().get(0).getChildren().get(2);
        assertEquals("img_girl.jpg", node.getAttributeValue("src"));
        assertEquals("500", node.getAttributeValue("width"));
        assertEquals("600", node.getAttributeValue("height"));
    }

    @Test
    void getAttributeValue3() {
        Node parse = HTMLParser.parse(sampleHTML3);
        Node node = parse.getChildren().get(0).getChildren().get(2);
        assertEquals("/action_page.php", node.getAttributeValue("action"));
        assertEquals("fname", node.getChildren().get(0).getAttributeValue("for"));
        assertEquals("text", node.getChildren().get(1).getAttributeValue("type"));
        assertEquals("fname", node.getChildren().get(1).getAttributeValue("name"));
        assertEquals("lname", node.getChildren().get(2).getAttributeValue("for"));
        assertEquals("submit", node.getChildren().get(5).getAttributeValue("type"));
        assertEquals("/action_page2.php", node.getChildren().get(5).getAttributeValue("formaction"));
        assertEquals("Submit as Admin", node.getChildren().get(5).getAttributeValue("value"));
    }
}