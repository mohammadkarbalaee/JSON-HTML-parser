package sbu.cs.parser.html;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node implements NodeInterface {
    private final List<Node> children;
    private final String htmlString;
    private final Map<String,String> attributes;
    private final String openingTag;
    private final String closingTag;

    public Node(String htmlString, String openingTag,
                String closingTag,
                Map<String,String> attributes) {
        this.attributes = attributes;
        this.htmlString = htmlString;
        this.closingTag = closingTag;
        this.openingTag = openingTag;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    @Override
    public String getStringInside() {
        return StringUtils.substringBetween(htmlString, openingTag, closingTag);
    }

    @Override
    public List<Node> getChildren() {
        for(int i = 0; i < children.size(); i++) {
            children.set(i, HTMLParser.parse(children.get(i).htmlString));
        }
        return children;
    }

    @Override
    public String getAttributeValue(String key) {
        return this.attributes.get(key);
    }
}