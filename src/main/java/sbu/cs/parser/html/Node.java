package sbu.cs.parser.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node implements NodeInterface {
    private final List<Node> children;
    private final String stringInside;
    private final Map<String,String> attributes;

    public Node(String stringInside,Map<String,String> attributes) {
        this.attributes = attributes;
        this.stringInside = stringInside;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    @Override
    public String getStringInside() {
        return this.stringInside;
    }

    @Override
    public List<Node> getChildren() {
        for(int i = 0; i < children.size(); i++) {
            children.set(i, HTMLParser.parse(children.get(i).getStringInside()));
        }
        return children;
    }

    @Override
    public String getAttributeValue(String key) {
        return this.attributes.get(key);
    }
}