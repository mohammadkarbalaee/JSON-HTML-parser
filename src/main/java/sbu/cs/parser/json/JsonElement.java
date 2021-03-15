package sbu.cs.parser.json;

public class JsonElement
{
    private String key;
    private String value;

    public JsonElement(String key,String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}