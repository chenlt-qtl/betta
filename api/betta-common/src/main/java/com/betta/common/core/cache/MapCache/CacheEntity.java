package com.betta.common.core.cache.MapCache;

import lombok.Data;

import java.time.Instant;

@Data
public class CacheEntity {

    public CacheEntity(Object value) {
        this.value = value;
    }

    public CacheEntity(Instant expireTime, Object value) {
        this.expireTime = expireTime;
        this.value = value;
    }

    private Instant expireTime = null;
    private Object value;
}
