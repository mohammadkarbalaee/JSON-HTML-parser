package sbu.cs.parser.html;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HTMLParser {

    private static String htmlString = "";

    public static Node parse(String html) {
        htmlString = html.replaceAll("\n", "").trim();
        Node madeNode = new Node(htmlString,
            getStartTag(),
            getEndTag(),
            attributesParser());

        removeEndAndStartTag();
        boolean hasTags = (htmlString.contains("<"));
        if(!hasTags) {
            return madeNode;
        }
        while(htmlString.length() > 0) {
            madeNode.addChild(initializeNode());
            //String before = htmlString;
            htmlString = removeTag();
            //String after = htmlString;
            //System.out.println(after.equals(before));
        }
        return madeNode;
    }

    private static String getStartTag() {
        return "<" + tagName() + attributes() + ">";
    }

    private static String getEndTag() {
        return "</" + tagName() + ">";
    }

    private static String attributes() {
        htmlString = htmlString.trim();
        int endOfStartTag = 0;
        for (int i = 0; htmlString.charAt(i) != '>'; i++) {
            endOfStartTag++;
        }
        String startTag = htmlString.substring(0, endOfStartTag + 1);
        return StringUtils.substringBetween(startTag, tagName(), ">");
    }

    private static String tagName() {
        htmlString = htmlString.trim();
        int charCounter = 0;
        for (int i  = 1; htmlString.charAt(i) != '>' && htmlString.charAt(i) != 32; i++)
            charCounter++;
        return htmlString.substring(1,charCounter + 1);
    }

    private static String getStringInside() {
        String startTag = "<" + tagName() + attributes() + ">";
        String endTag = "</" + tagName() + ">";
        return StringUtils.substringBetween(htmlString, startTag, endTag);
    }

    private static void removeEndAndStartTag() {
        htmlString = htmlString.replaceFirst(getStartTag(), "")
            .replaceFirst(getEndTag(), "").trim();
    }

    private static String removeTag() {
        String startTag = getStartTag();
        boolean isSingleTag = startTag.indexOf('<') + 1 == startTag.indexOf('/');
        System.out.println("opening\n" + startTag);
        System.out.println("whole\n" + htmlString);
        if (isSingleTag) {
            return htmlString.replaceFirst(startTag, "").trim();
        }
        return htmlString.replaceFirst(startTag + getStringInside()
            + getEndTag(), "").trim();
    }

    private static Node initializeNode() {
        String startTag = getStartTag();
        boolean isSingleTag = startTag.indexOf('<') + 1 == startTag.indexOf('/');
        if (isSingleTag) {
            return new Node((startTag + getStringInside()).trim(),
                getStartTag(),
                getEndTag(),
                attributesParser());
        }
        return new Node(startTag + getStringInside() + getEndTag(),
            getStartTag(),
            getEndTag(),
            attributesParser());
    }

    private static Map<String,String> attributesParser() {
        String attributes = attributes();
        if (attributes.length() != 0) {
            String[] keyAndValuePairs = attributes.split("\" ");
            HashMap<String,String> attributesPair = new HashMap<>();
            for (String keyValuePair : keyAndValuePairs) {
                String[] parsePair = keyValuePair.split("=");
                attributesPair.put(parsePair[0].replaceAll("\"",""),
                    parsePair[1].replaceAll("\"",""));
            }
            return attributesPair;
        } else {
         return new HashMap<>();
        }
    }

    public static String toHTMLString(Node root) {
        return root.getOpeningTag() + root.getStringInside() + root.getClosingTag();
    }
}
