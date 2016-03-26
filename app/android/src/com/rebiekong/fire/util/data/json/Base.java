package com.rebiekong.fire.util.data.json;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 18029 on 2015/12/26.
 */
public class Base {
    private Map<String, Object> jsonData;
    private String jsonString;

    public Base() {
    }

    public Base(String json) {
        jsonString = json;
        jsonData = toMap(json);
    }

    public static Map<String, Object> toMap(String json) {
        Map result = new HashMap();
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator iterator = jsonObject.keys();
            String key, value;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                value = jsonObject.getString(key);
                result.put(key, value);
            }
        } catch (JSONException e) {
        }
        return result;
    }

    public String getJsonString() {
        return jsonString;
    }

    public String getString(String path) {
        ArrayList<String> realPath = getPath(path);
        String value = "";
        Map temp = jsonData;
        try {
            for (int ct = 0; ct < realPath.size(); ct++) {
                String key = realPath.get(ct);
                Object valueTemp = temp.get(key);
                if (ct == (realPath.size() - 1)) {
                    value = (String) valueTemp;
                    break;
                } else {
                    temp = toMap((String) valueTemp);
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return value;
    }

    private ArrayList<String> getPath(String path) {
        return getPath(path, "_");
    }

    private ArrayList<String> getPath(String path, String delimiter) {
        ArrayList<String> realPath = new ArrayList<>();
        String[] tempPath = path.split(delimiter);
        for (int ct = 0; ct < tempPath.length; ct++) {
            realPath.add(tempPath[ct]);
        }
        return realPath;
    }
}
