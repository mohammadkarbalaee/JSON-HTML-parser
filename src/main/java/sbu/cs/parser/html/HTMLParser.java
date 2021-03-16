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
            madeNode.addChild(initializeTag(htmlText));
            htmlText = deleteTag(htmlText);
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
        int endOfStartTag = 0;
        for (int i = 0; tag.charAt(i) != '>'; i++)
            endOfStartTag++;
        String startTag = tag.substring(0, endOfStartTag + 1);
        return StringUtils.substringBetween(startTag, tagName(tag), ">");
    }

    private static String tagName(String tag)
    {
        int charCounter = 0;
        for (int i  = 1; tag.charAt(i) != '>' && tag.charAt(i) != 32; i++)
            charCounter++;
        return tag.substring(1,charCounter + 1);
    }

    private static String removeEndAndStartTagName(String htmlText)
    {
        return htmlText.replaceFirst(getStartTag(htmlText), "").replaceFirst(getEngTag(htmlText), "");
    }

    private static String deleteTag(String html)
    {
        String temp = html.substring(html.indexOf('<'), html.indexOf('>') + 1);
        if((html.indexOf('<') + 1) == (html.indexOf('/')))
            return html.replaceFirst(temp, "").trim();
        else
        {
            String openTag = temp;
            String tagName = html.substring(html.indexOf('<') + 1, Math.min(html.indexOf('>'), html.indexOf(' ')));
            String closeTag = "</" + tagName + ">";
            String tag = html.substring(html.indexOf(openTag) + openTag.length(), html.indexOf(closeTag));
            return html.replaceFirst(openTag + tag + closeTag, "").trim();
        }
    }
    private static Node initializeTag(String htmlText)
    {
        String newHtmlText = htmlText.substring(htmlText.indexOf('<'), htmlText.lastIndexOf('>') + 1);
        if((htmlText.indexOf('<') + 1) == (htmlText.indexOf('/')))
            return new Node(newHtmlText.trim());
        else
        {
            String startTag = getStartTag(htmlText);
            String closeTag =  getEngTag(htmlText);
            String tagBody = getStringInside(htmlText);
            return new Node(startTag + tagBody + closeTag);
        }
    }

    private static String getStringInside(String htmlText)
    {
        String startTag = "<" + tagName(htmlText) + attributes(htmlText) + ">";
        String endTag = "</" + tagName(htmlText) + ">";
        return StringUtils.substringBetween(htmlText, startTag, endTag);
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
