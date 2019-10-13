package com.wang.server.gateway.bean;


import org.springframework.cloud.gateway.config.GatewayProperties;

import java.util.List;

public class DynamicRoutesBean {

    private GatewayProperties dynamicRoutes;

    // 业主APP服务端API 多个Path共享限流数量 路径组
    private List<String> apiCommunityShareLimitList;

    public GatewayProperties getDynamicRoutes() {
        return dynamicRoutes;
    }

    public void setDynamicRoutes(GatewayProperties dynamicRoutes) {
        this.dynamicRoutes = dynamicRoutes;
    }

    public List<String> getApiCommunityShareLimitList() {
        return apiCommunityShareLimitList;
    }

    public void setApiCommunityShareLimitList(List<String> apiCommunityShareLimitList) {
        this.apiCommunityShareLimitList = apiCommunityShareLimitList;
    }
}
