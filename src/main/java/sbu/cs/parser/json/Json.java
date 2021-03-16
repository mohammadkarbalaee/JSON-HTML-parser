package sbu.cs.parser.json;

import java.util.ArrayList;

public class Json implements JsonInterface
{
    private ArrayList<JsonElement> elementsList;
    private String key;
    public Json(ArrayList<JsonElement> elementsList)
    {
        this.elementsList = elementsList;
    }

    @Override
    public String getStringValue(String key)
    {
        this.key = key;
        for (JsonElement element : elementsList)
        {
            if (element.getKey().equals(key))
            {
                return element.getValue();
            }
        }
        return null;
    }

    public void getValue()
    {
        String value = getStringValue(key);
        if (value.charAt(0) == '[')
        {
            value = value.replaceAll("\\[|]","");
            String[] parsed = value.split(",");
            if (parsed[0].equals("true") || parsed[0].equals("false"))
            {
                boolean[] booleanArray = new boolean[parsed.length];
                int i = 0;
                for (String temp : parsed)
                {
                    temp = temp.trim();
                    if (temp.equals("true"))
                    {
                        booleanArray[i] = true;
                        i++;
                    }
                    else
                    {
                        booleanArray[i] = false;
                        i++;
                    }
                }
            }
            else if (parsed[0].length() == 1 && parsed[0].matches("[^\\d]"))
            {
                char[] charArray = new char[parsed.length];
                int i = 0;
                for (String temp : parsed)
                {
                    temp = temp.trim();
                    charArray[i] = temp.charAt(0);
                    i++;
                }
            }
            else if (parsed[0].matches("\\d+.\\d+"))
            {
                double[] doubleArray = new double[parsed.length];
                int i = 0;
                for (String temp : parsed)
                {
                    temp = temp.trim();
                    doubleArray[i] = Double.parseDouble(temp);
                    i++;
                }
            }
            else if (parsed[0].matches("\\d+"))
            {
                int[] intArray = new int[parsed.length];
                int i = 0;
                for (String temp : parsed)
                {
                    temp = temp.trim();
                    intArray[i] = Integer.parseInt(temp);
                    i++;
                }
            }
            else
            {
                String[] stringArray = new String[parsed.length];
                int i = 0;
                for (String temp : parsed)
                {
                    temp = temp.trim();
                    stringArray[i] = temp;
                    i++;
                }
            }
        }
        else if (value.matches("\\d+.\\d+"))
        {
            double doubleValue = Double.parseDouble(value);
        }
        else if (value.matches("\\d+"))
        {
            int integerValue = Integer.parseInt(value);
        }
        else if (value.equals("true") || value.equals("false"))
        {
            boolean booleanValue = value.equals("true");
        }
        else if (value.equals("null"))
        {
            String nullValue = null;
        }
        else
        {
            String stringValue = value;
        }
    }
}