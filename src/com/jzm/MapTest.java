package com.jzm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> cMap = new ConcurrentHashMap();
        cMap.put("a", 1);
        cMap.put("b", 2);

        for (String key: cMap.keySet()) {
            String k = (String)key;
            Object v = cMap.get(k);

            System.out.println(k + ", " + v);
        }

        for (Map.Entry<String, Object> entry: cMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

    }
}
