package sbu.cs.parser.html;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HTMLParser {
    public static Node parse(String html) {
        html = html.replaceAll("\n", "").trim();
        Node madeNode = new Node(html,
            getStartTag(html),
            getEndTag(html));

        html = removeEndAndStartTag(html);
        boolean hasTags = (html.contains("<"));
        if(!hasTags) {
            return madeNode;
        }
        while(html.length() > 0) {
            madeNode.addChild(initializeNode(html));
            html = removeTag(html);
        }
        return madeNode;
    }

    private static String getStartTag(String html) {
        return "<" + tagName(html) + attributes(html) + ">";
    }

    private static String getEndTag(String html) {
        return "</" + tagName(html) + ">";
    }

    private static String attributes(String html) {
        int endOfStartTag = 0;
        for (int i = 0; html.charAt(i) != '>'; i++)
            endOfStartTag++;
        String startTag = html.substring(0, endOfStartTag + 1);
        return StringUtils.substringBetween(startTag, tagName(html), ">");
    }

    private static String tagName(String htmlString) {
        htmlString = htmlString.trim();
        int charCounter = 0;
        for (int i  = 1; htmlString.charAt(i) != '>' && htmlString.charAt(i) != 32; i++)
            charCounter++;
        return htmlString.substring(1,charCounter + 1);
    }

    private static String getStringInside(String html) {
        String startTag = "<" + tagName(html) + attributes(html) + ">";
        String endTag = "</" + tagName(html) + ">";
        return StringUtils.substringBetween(html, startTag, endTag);
    }

    private static String removeEndAndStartTag(String html) {
        return html.replaceFirst(getStartTag(html), "")
            .replaceFirst(getEndTag(html), "").trim();
    }

    private static String removeTag(String html) {
        String startTag = getStartTag(html);
        boolean isSingleTag = startTag.indexOf('<') + 1 == startTag.indexOf('/');
        if (isSingleTag) {
            return html.replaceFirst(startTag, "").trim();
        }
        return html.replaceFirst(startTag + getStringInside(html)
            + getEndTag(html), "").trim();
    }

    private static Node initializeNode(String html) {
        String startTag = getStartTag(html);
        boolean isSingleTag = startTag.indexOf('<') + 1 == startTag.indexOf('/');
        if (isSingleTag) {
            return new Node((startTag + getStringInside(html)).trim(),
                getStartTag(html),
                getEndTag(html));
        }
        return new Node(startTag + getStringInside(html) + getEndTag(html),
            getStartTag(html),
            getEndTag(html));
    }

    public static Map<String,String> attributesParser(String htmlString) {
        String trimmedAttributes = attributes(htmlString).trim();
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
