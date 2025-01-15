package com.betta.common.core.cache;

public interface CacheUtils {
    public <T> void setObject(final String group, final String key, final T value);

    public Boolean hasKey(final String group, String key);

    public <T> T getObject(final String group, final String key);

    public boolean deleteObject(final String group, final String key);
}
