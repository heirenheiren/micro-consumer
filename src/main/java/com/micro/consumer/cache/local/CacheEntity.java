package com.micro.consumer.cache.local;

import java.io.Serializable;

import lombok.Data;

@Data
public class CacheEntity implements Serializable {
    private static final long serialVersionUID = 7172649826282703560L;
    /**
     * 值
     */
    private Object value;
    /**
     * 保存的时间戳
     */
    private long gmtModify;
    /**
     * 过期时间
     */
    private int expire;
    public CacheEntity(Object value, long gmtModify, int expire) {
        super();
        this.value = value;
        this.gmtModify = gmtModify;
        this.expire = expire;
    }
}
