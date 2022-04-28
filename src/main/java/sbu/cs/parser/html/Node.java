package sbu.cs.parser.html;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node implements NodeInterface {
    private final List<Node> children;
    private final String htmlText;

    public Node(String value) {
        this.children = new ArrayList<>();
        this.htmlText = value;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    private String attributes() {
        int endOfStartTag = 0;
        for (int i = 0; htmlText.charAt(i) != '>'; i++) {
            endOfStartTag++;
        }
        String startTag = htmlText.substring(0, endOfStartTag + 1);
        return StringUtils.substringBetween(startTag, tagName(), ">");
    }

    private String tagName() {
        int tagNameLength = 0;
        for (int i = 1; this.htmlText.charAt(i) != '>' && this.htmlText.charAt(i) != 32; i++) {
            tagNameLength++;
        }
        return this.htmlText.substring(1,tagNameLength + 1);
    }

    public String getStartTag() {
        return "<" + this.tagName() + this.attributes() + ">";
    }

    public String getEndTag() {
        return "</" + this.tagName() + ">";
    }

    @Override
    public String getStringInside() {
        String startTag = this.getStartTag();
        String endTag = this.getEndTag();
        return StringUtils.substringBetween(this.htmlText, startTag, endTag);
    }

    @Override
    public List<Node> getChildren() {
        for(int i = 0; i < children.size(); i++) {
            Node temp = HTMLParser.parse(children.get(i).htmlText);
            children.set(i, temp);
        }
        return children;

    }

    private ArrayList<String> attributesParser() {
        ArrayList<String> attributesList = new ArrayList<>();
        String trimmedAttributes = attributes().trim();
        String[] keyAndValuePair = trimmedAttributes.split("\" ");
        for (String temp : keyAndValuePair) {
            String[] tmpStr = temp.split("=");
            attributesList.add(tmpStr[0].replaceAll("\"",""));
            attributesList.add(tmpStr[1].replaceAll("\"",""));
        }
        return attributesList;
    }


    @Override
    public String getAttributeValue(String key) {
        HashMap<String,String> attributesPair = new HashMap<>();
        ArrayList<String> parsedAttributes = this.attributesParser();
        for (int i = 0; i < parsedAttributes.size(); i += 2) {
            attributesPair.put(parsedAttributes.get(i),parsedAttributes.get(i + 1));
        }
        return attributesPair.get(key);
    }
}