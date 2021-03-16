package sbu.cs.parser.html;

import org.apache.commons.lang3.StringUtils;

public class App
{
    public static void main(String[] args)
    {
//        String temp = removeEndAndStartTagName("<br><body>hello</body></br>");
        System.out.println("kirrrr");
    }
//    public static String getStartTag(String tag)
//    {
//        return "<" + tagName(tag) + attributes(tag) + ">";
//    }
//
//    public static String getEngTag(String tag)
//    {
//        return "</" + tagName(tag) + ">";
//    }
//
//    public static String attributes(String tag)
//    {
//        int endOfStartTag = 0;
//        for (int i = 0; tag.charAt(i) != '>'; i++)
//            endOfStartTag++;
//        String startTag = tag.substring(0, endOfStartTag + 1);
//        return StringUtils.substringBetween(startTag, tagName(tag), ">");
//    }
//
//    public static String tagName(String tag)
//    {
//        int charCounter = 0;
//        for (int i  = 1; tag.charAt(i) != '>' && tag.charAt(i) != 32; i++)
//            charCounter++;
//        return tag.substring(1,charCounter + 1);
//    }
//
//    public static String removeEndAndStartTagName(String htmlText)
//    {
//        return htmlText.replaceFirst(getStartTag(htmlText), "").replaceFirst(getEngTag(htmlText), "");
//    }
}