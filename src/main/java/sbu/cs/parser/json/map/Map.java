package sbu.cs.parser.json.map;

import java.util.ArrayList;

public class Map {
  private ArrayList<String> keys;
  private ArrayList values;

  public Map() {
    this.keys = new ArrayList<>();
    this.values = new ArrayList<>();
  }

  public void put(String newKey,Object newValue) {
    if (isNewKey(newKey)) {
      this.keys.add(newKey);
      this.values.add(newValue);
    } else {
      this.values.set(this.keys.indexOf(newKey),newValue);
    }
  }

  public Object get(String key) {
    return this.values.get(this.keys.indexOf(key));
  }

  public void clear() {
    this.keys = new ArrayList<>();
    this.values = new ArrayList<>();
  }

  private boolean isNewKey(String keyToAdd) {
    boolean isNew = true;
    for (String key: this.keys) {
      if (keyToAdd.equals(key)) {
        isNew = false;
        break;
      }
    }
    return isNew;
  }

  public ArrayList<String> getKeys() {
    return this.keys;
  }

  public ArrayList getValues() {
    return this.values;
  }

  public void setKeys(ArrayList<String> keys) {
    this.keys = keys;
  }

  public void setValues(ArrayList values) {
    this.values = values;
  }
}
