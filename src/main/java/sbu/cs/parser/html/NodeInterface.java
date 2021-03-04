package sbu.cs.parser.html;

import java.util.List;

public interface NodeInterface {

    public List<Node> getChildren();
    public String getStringInside();
    public String getAttributeValue(String key);
}
