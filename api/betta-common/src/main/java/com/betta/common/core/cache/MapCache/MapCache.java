package com.betta.common.core.cache.MapCache;

import com.betta.common.core.cache.CacheUtils;
import com.betta.common.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class MapCache implements CacheUtils {

    private static final Map<String, CacheEntity> map = new HashMap<>();

    private static final Map<String, Collection<String>> keyMap = new HashMap<>();

    @Override
    public <T> void setObject(String key, T value) {
        setObject(key, value, 0, null);
    }

    @Override
    public Boolean hasKey(String key) {
        return map.containsKey(key);
    }

    @Override
    public <T> T getObject(String key) {
        if (map.containsKey(key)) {
            CacheEntity cacheEntity = map.get(key);
            if (cacheEntity.getExpireTime() != null) {
                if (Instant.now().isBefore(cacheEntity.getExpireTime())) {
                    return (T) cacheEntity.getValue();
                }
            } else {
                return (T) cacheEntity.getValue();
            }
        }
        return null;
    }

    @Override
    public void deleteObject(String key) {
        removeKey(key);
        map.remove(key);
    }

    @Override
    public void deleteObject(Collection<String> keys) {
        keys.forEach(this::deleteObject);
    }

    @Override
    public <T> void setObject(String key, T value, Integer timeout, ChronoUnit timeUnit) {
        Instant instant = null;
        if (timeout > 0) {
            instant = Instant.now().plus(timeout, timeUnit);
        }
        addKey(key);
        map.put(key, new CacheEntity(instant, value));
    }

    @Override
    public Collection<String> keys(String pattern) {
        return keyMap.get(pattern);
    }

    public void addKey(String key) {
        String pattern = key.substring(0, key.indexOf(":") + 1);
        Collection<String> keys;
        if (StringUtils.isNotEmpty(pattern)) {
            if (keyMap.containsKey(pattern)) {
                keys = keyMap.get(pattern);
            } else {
                keys = new ArrayList<>();
                keyMap.put(pattern, keys);
            }
            keys.add(key);
        }
    }

    public void removeKey(String key) {
        String pattern = key.substring(0, key.indexOf(":") + 1);
        Collection<String> keys;
        if (StringUtils.isNotEmpty(pattern)) {
            if (keyMap.containsKey(pattern)) {
                keys = keyMap.get(pattern);
                keys.remove(key);
            }
        }
    }

}
