package sbu.cs.parser.html;

import org.apache.commons.lang3.StringUtils;

public class HTMLParser
{
    public static Node parse(String htmlText)
    {
        htmlText = htmlText.replaceAll("\n", "");
        htmlText = htmlText.trim();
        Node madeNode = new Node(htmlText);
        htmlText = removeEndAndStartTagName(htmlText);
        if(!(htmlText.contains("<")))
            return madeNode;
        while(htmlText.length() != 0)
        {
            madeNode.addChild(initializeNode(htmlText));
            htmlText = removeTag(htmlText);
        }
        return madeNode;
    }

    private static String getStartTag(String tag)
    {
        return "<" + tagName(tag) + attributes(tag) + ">";
    }

    private static String getEngTag(String tag)
    {
        return "</" + tagName(tag) + ">";
    }

    private static String attributes(String tag)
    {
        tag = tag.trim();
        int endOfStartTag = 0;
        for (int i = 0; tag.charAt(i) != '>'; i++)
            endOfStartTag++;
        String startTag = tag.substring(0, endOfStartTag + 1);
        return StringUtils.substringBetween(startTag, tagName(tag), ">");
    }

    private static String tagName(String tag)
    {
        tag = tag.trim();
        int charCounter = 0;
        for (int i  = 1; tag.charAt(i) != '>' && tag.charAt(i) != 32; i++)
            charCounter++;
        return tag.substring(1,charCounter + 1);
    }

    private static String getStringInside(String htmlText)
    {
        String startTag = "<" + tagName(htmlText) + attributes(htmlText) + ">";
        String endTag = "</" + tagName(htmlText) + ">";
        return StringUtils.substringBetween(htmlText, startTag, endTag);
    }

    private static String removeEndAndStartTagName(String htmlText)
    {
        return htmlText.replaceFirst(getStartTag(htmlText), "").replaceFirst(getEngTag(htmlText), "");
    }

    private static String removeTag(String htmlText)
    {
        if (getStartTag(htmlText).indexOf('<') + 1 == getStartTag(htmlText).indexOf('/'))
            return htmlText.replaceFirst(getStartTag(htmlText), "").trim();
        return htmlText.replaceFirst(getStartTag(htmlText) +getStringInside(htmlText) + getEngTag(htmlText), "").trim();
    }

    private static Node initializeNode(String htmlText)
    {
        if (getStartTag(htmlText).indexOf('<') + 1 == getStartTag(htmlText).indexOf('/'))
            return new Node((getStartTag(htmlText) + getStringInside(htmlText)).trim());
        return new Node(getStartTag(htmlText) + getStringInside(htmlText) + getEngTag(htmlText));
    }


    /*
    * a function that will return string representation of dom object.
    * only implement this after all other functions been implemented because this
    * impl is not required for this series of exercises. this is for more score
     */
    public static String toHTMLString(Node root)
    {
        // TODO implement this for more score
        return null;
    }
}
