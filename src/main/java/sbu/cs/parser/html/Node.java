package sbu.cs.parser.html;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node implements NodeInterface
{

    private List<Node> children = null;
    private String value;

    public Node(String value)
    {
        this.children = new ArrayList<>();
        this.value = value;
    }

    public void addChild(Node child)
    {
        children.add(child);
    }

    private String attributes()
    {
        int endOfStartTag = 0;
        for (int i = 0; value.charAt(i) != '>'; i++)
            endOfStartTag++;
        String startTag = value.substring(0, endOfStartTag + 1);
        return StringUtils.substringBetween(startTag, tagName(), ">");
    }

    private String tagName()
    {
        int charCounter = 0;
        for (int i  = 1; value.charAt(i) != '>' && value.charAt(i) != 32; i++)
            charCounter++;
        return value.substring(1,charCounter + 1);
    }

    @Override
    public String getStringInside()
    {
        String startTag = "<" + tagName() + attributes() + ">";
        String endTag = "</" + tagName() + ">";
        return StringUtils.substringBetween(value, startTag, endTag);
    }

    @Override
    public List<Node> getChildren()
    {
        return children;
    }

    private ArrayList<String> attributesParser()
    {
        ArrayList<String> attributesList = new ArrayList<>();
        String trimmedAttributes = attributes().trim();
        String[] keyAndValuePair = trimmedAttributes.split(" ");
        for (String temp : keyAndValuePair)
        {
            String[] tmpStr = temp.split("=");
            attributesList.add(tmpStr[0]);
            attributesList.add(tmpStr[1]);
        }
        return attributesList;
    }

    private Map<String,String> attributesHashmap()
    {
        HashMap<String,String> attributesPair = new HashMap<>();
        ArrayList<String> parsedAttributes = attributesParser();
        for (int i = 0; i < parsedAttributes.size(); i += 2)
        {
            attributesPair.put(parsedAttributes.get(i),parsedAttributes.get(i + 1));
        }
        return attributesPair;
    }

    @Override
    public String getAttributeValue(String key)
    {
        Map<String,String> attributesPair = attributesHashmap();
        return attributesPair.get(key);
    }
}