package com.betta.common.core.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapCache implements CacheUtils{

    private static final Map<String,Map<String,Object>> map = new HashMap<>();

    @Override
    public <T> void setObject(String group,String key, T value) {
        Map<String,Object> subMap;
        if(map.containsKey(group)){
            subMap = map.get(group);
        }else {
            subMap = new HashMap<>();
            map.put(group,subMap);
        }
        subMap.put(key,value);
    }

    @Override
    public Boolean hasKey(String group,String key) {
        Map<String, Object> subMap = map.get(group);
        return subMap.containsKey(key);
    }

    @Override
    public <T> T getObject(String group,String key) {
        return null;
    }

    @Override
    public boolean deleteObject(String group,String key) {
        return false;
    }

    @Override
    public boolean deleteGroup(String group) {
        return false;
    }
}
