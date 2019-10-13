package com.wang.server.gateway.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import com.wang.server.gateway.bean.DynamicRoutesBean;
import com.wang.server.gateway.support.LocalCacheUtils;

@Component
public class ConfigServerDataConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ConfigServerDataConfiguration.class);

    // 读取文件时长
    public static final int SCHEDULE_FIXED_RATE = 30000;

    @Value("${dynamic.routes.ymlfilePath:/application-dynamicRoutes.yml}")
    private String ymlfilePath;

    // 定时加载文件内容
    @Scheduled(fixedRate = SCHEDULE_FIXED_RATE)
    public void scheduleExec(){
        initRoutesData();
    }

    void initRoutesData(){
        Yaml yaml = new Yaml();
        if (new File(ymlfilePath).exists()) {
            // 从jar外部读取文件
            try {
                LocalCacheUtils.builder().setDynamicRoutesDataCache(yaml.loadAs(new FileInputStream(ymlfilePath), DynamicRoutesBean.class)).build();
            } catch (FileNotFoundException e) {
                logger.error("严重！严重！读取配置文件数据失败，路由信息失效/信息：{}", e);
            }
        } else {
            // jar内部读取方式
            try {
                LocalCacheUtils.builder().setDynamicRoutesDataCache(yaml.loadAs(ConfigServerDataConfiguration.class.getResourceAsStream(ymlfilePath), DynamicRoutesBean.class)).build();
            } catch (Exception e) {
                logger.error("严重！严重！读取配置文件数据失败，路由信息失效/信息：{}", e);
            }
        }
    }
}
