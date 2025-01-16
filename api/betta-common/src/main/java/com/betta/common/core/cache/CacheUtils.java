package com.betta.common.core.cache;

import java.time.temporal.ChronoUnit;
import java.util.Collection;

public interface CacheUtils {
    public <T> void setObject(final String key, final T value);

    public Boolean hasKey(String key);

    public <T> T getObject(final String key);

    public void deleteObject(final String key);

    public void deleteObject(Collection<String> keys);

    public <T> void setObject(final String key, final T value, final Integer timeout, final ChronoUnit timeUnit);

    public Collection<String> keys(final String pattern);
}
