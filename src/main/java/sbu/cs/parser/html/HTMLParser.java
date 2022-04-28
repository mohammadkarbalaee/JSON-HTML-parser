package sbu.cs.parser.html;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HTMLParser {
    public static Node parse(String html) {
        html = html.replaceAll("\n", "").trim();
        Node madeNode = new Node(html, getOpeningTag(html), getClosingTag(html));
        html = removeClosingAndOpeningTag(html);
        boolean hasTags = (html.contains("<"));
        if(!hasTags) {
            return madeNode;
        }
        while(html.length() > 0) {
            madeNode.addChild(initializeNode(html));
            html = removeFirstTag(html);
        }
        return madeNode;
    }

    private static String getOpeningTag(String html) {
        return "<" + getTagName(html) + getAttributes(html) + ">";
    }

    private static String getClosingTag(String html) {
        return "</" + getTagName(html) + ">";
    }

    private static String getAttributes(String html) {
        String startTag = html.substring(0, html.indexOf(">") + 1);
        return StringUtils.substringBetween(startTag, getTagName(html), ">");
    }

    private static String getTagName(String html) {
        int nameLength = 0;
        for (int i  = 1; html.charAt(i) != '>' && html.charAt(i) != 32; i++) {
            nameLength++;
        }
        return html.substring(1,nameLength + 1);
    }

    private static String getStringInside(String html) {
        String startTag = "<" + getTagName(html) + getAttributes(html) + ">";
        String endTag = "</" + getTagName(html) + ">";
        return StringUtils.substringBetween(html, startTag, endTag);
    }

    private static String removeClosingAndOpeningTag(String html) {
        return html.replaceFirst(getOpeningTag(html), "")
            .replaceFirst(getClosingTag(html), "").trim();
    }

    private static String removeFirstTag(String html) {
        String startTag = getOpeningTag(html);
        boolean isSingleTag = startTag.indexOf('<') + 1 == startTag.indexOf('/');
        if (isSingleTag) {
            return html.replaceFirst(startTag, "").trim();
        }
        return html.replaceFirst(startTag + getStringInside(html)
            + getClosingTag(html), "").trim();
    }

    private static Node initializeNode(String html) {
        String startTag = getOpeningTag(html);
        boolean isSingleTag = startTag.indexOf('<') + 1 == startTag.indexOf('/');
        if (isSingleTag) {
            return new Node((startTag + getStringInside(html)).trim(), getOpeningTag(html), getClosingTag(html));
        }
        return new Node(startTag + getStringInside(html) + getClosingTag(html), getOpeningTag(html), getClosingTag(html));
    }

    public static Map<String,String> attributesParser(String htmlString) {
        String trimmedAttributes = getAttributes(htmlString).trim();
        String[] keyAndValuePairs = trimmedAttributes.split("\" ");
        HashMap<String,String> attributesPair = new HashMap<>();
        for (String keyValuePair : keyAndValuePairs) {
            String[] parsePair = keyValuePair.split("=");
            attributesPair.put(parsePair[0].replaceAll("\"",""),
                parsePair[1].replaceAll("\"",""));
        }
        return attributesPair;
    }

    public static String toHTMLString(Node root) {
        return root.getOpeningTag() + root.getStringInside() + root.getClosingTag();
    }
}
