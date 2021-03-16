package sbu.cs.parser.json;

public class JsonElement
{
    protected String key;
    protected String value;

    public JsonElement(String key, String value)
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