package sbu.cs.parser.json;

import java.util.ArrayList;

public class JsonParser
{
    private static ArrayList<JsonElement> elementsList = new ArrayList<>();
    public static Json parse(String data)
    {
        String tempStr = data;
        StringBuilder temp = new StringBuilder(tempStr);
        for (int i = 0; i < temp.length(); i++)
        {
            if (temp.charAt(i) == '[')
            {
                for (int j = i + 1; temp.charAt(j) != ']'; j++)
                {
                    if (temp.charAt(j) == ',')
                    {
                        temp.setCharAt(j,'@');
                    }
                }
            }
        }
        data = temp.toString();
        data = data.replaceAll("(\\{)|(})","");
        String[] keyAndValuePairs = data.split(",");
        for (String keyAndValuePair : keyAndValuePairs)
        {
            String[] separated = keyAndValuePair.split(":");
            separated[0] = separated[0].trim();
            separated[1] = separated[1].trim();
            separated[0] = separated[0].replaceAll("\"","");
            separated[1] = separated[1].replaceAll("\"","");
            separated[1] = separated[1].replaceAll("@",",");
            JsonElement element = new JsonElement(separated[0],separated[1]);
            elementsList.add(element);
        }
        return new Json(elementsList);
    }
}