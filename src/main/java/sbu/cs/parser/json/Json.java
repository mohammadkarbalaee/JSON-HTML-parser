package sbu.cs.parser.json;

import java.util.ArrayList;

public class Json implements JsonInterface
{
    private ArrayList<JsonElement> elementsList;
    public Json(ArrayList<JsonElement> elementsList)
    {
        this.elementsList = elementsList;
    }

    @Override
    public String getStringValue(String key)
    {
        for (JsonElement element : elementsList)
        {
            if (element.getKey().equals(key))
            {
                return element.getValue();
            }
        }
        return null;
    }
}