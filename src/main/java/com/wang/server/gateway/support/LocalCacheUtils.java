package com.wang.server.gateway.support;

import com.wang.server.gateway.bean.DynamicRoutesBean;

/**
 * 本地缓存
 */
public class LocalCacheUtils {

    // 缓存路由信息
    private static DynamicRoutesBean dynamicRoutesDataCache;

    public static DynamicRoutesBean getDynamicRoutesDataCache() {
        return dynamicRoutesDataCache;
    }

    public static class Builder {
        private DynamicRoutesBean dynamicRoutesDataCache;

        public Builder setDynamicRoutesDataCache(DynamicRoutesBean dynamicRoutesDataCache) {
            this.dynamicRoutesDataCache = dynamicRoutesDataCache;
            return this;
        }

        public void build(){
            LocalCacheUtils.dynamicRoutesDataCache = this.dynamicRoutesDataCache;
        }
    }

    public static Builder builder(){
        return new Builder();
    }

    private LocalCacheUtils() {
        // 私有构造
    }
}
