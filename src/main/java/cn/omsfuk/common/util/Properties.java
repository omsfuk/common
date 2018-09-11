package cn.omsfuk.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * by omsfuk
 * ---- 创建于 9/11/18 5:58 PM
 */
public class Properties {

    private Map<String, String> map = new HashMap<>();

    public Properties() {
    }

    public void addProperty(String key, String value) {
        map.put(key, value);
    }

    public void removeProperty(String key) {
        map.remove(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(map.get(key));
    }

    public String getString(String key) {
        return map.get(key);
    }

    public double getDouble(String key) {
        return Double.parseDouble(key);
    }

    public float getFloat(String key) {
        return Float.parseFloat(map.get(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(map.get(key));
    }

    public boolean containKey(String key) {
        return map.containsKey(key);
    }
}
