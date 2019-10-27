package org.jeecg.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocalCache {

    private static String valueStr = "value";
    private static String vaildMsStr = "vaildMs";
    private static String saveLongTimeStr = "saveLongTime";

    private static ConcurrentMap<String, Object> cache = new ConcurrentHashMap();

    static {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Set<String> delKeySet = new HashSet<>();
                    if (cache != null && !cache.isEmpty()) {
                        for (Map.Entry<String, Object> obj : cache.entrySet()) {
                            if (obj.getValue() != null) {
                                Map<String, Object> cacheData = (Map<String, Object>) obj.getValue();
                                Long saveTime = (Long) cacheData.get(saveLongTimeStr);
                                Long vaildMs = (Long) cacheData.get(vaildMsStr);
                                if(vaildMs==0){
                                    //为0不删除
                                    continue;
                                }
                                if ((new Date().getTime() - saveTime) >= vaildMs) {
                                    delKeySet.add(obj.getKey());
                                }
                            }
                        }
                        for (String key : delKeySet) {
                            cache.remove(key);
                        }
                    }
                }
            }
        };
        t1.start();
    }

    //vaildMs为0标识永久存在不自动删除
    public static void addCache(String key, String value, Long vaildMs) {
        Map<String, Object> obj = new HashMap<>();
        obj.put(valueStr, value);
        obj.put(vaildMsStr, vaildMs);
        obj.put(saveLongTimeStr, new Date().getTime());
        cache.put(key, obj);
    }

    public static void delCache(String key) {
        cache.remove(key);
    }

    public static String getCache(String key) {
        Map<String,Object> data= (Map<String, Object>) cache.get(key);
        if(data!=null){
            return (String) data.get(valueStr);
        }else{
            return null;
        }
    }

}
