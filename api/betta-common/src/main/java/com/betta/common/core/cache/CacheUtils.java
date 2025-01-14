package com.betta.common.core.cache;

public interface CacheUtils {
    public <T> void setObject(final String key, final T value);
    public Boolean hasKey(String key);

    public <T> T getObject(final String key);

    public boolean deleteObject(final String key);
}
