package com.betta.common.core.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapCache implements CacheUtils{

    private static final Map<String,Map> map = new HashMap<>();

    @Override
    public <T> void setObject(String key, T value) {

    }

    @Override
    public Boolean hasKey(String key) {
        return null;
    }

    @Override
    public <T> T getObject(String key) {
        return null;
    }

    @Override
    public boolean deleteObject(String key) {
        return false;
    }
}
