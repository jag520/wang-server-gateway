package com.wang.server.gateway.config;

import com.wang.server.gateway.support.LocalCacheUtils;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.server.PathContainer;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class RateLimiterKeyResolverConfiguration {

    /**
     * 限流方式：url路径（不包括参数）
     * @return
     */
    @Bean
    KeyResolver urlKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * 限流方式：ip
     * @return
     */
    @Bean
    KeyResolver ipKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 限流方式：所有请求
     * @return
     */
    @Bean
    KeyResolver allKeyResolver() {
        return exchange -> Mono.just("/");
    }


    /**
     * 物管APP接口 特殊处理
     * @return
     */
    @Bean
    KeyResolver communityKeyResolver(){
        return exchange -> {
            String path = exchange.getRequest().getPath().value(); //  示例：/manage/xxxxxx
            String finalStr = exchange.getRequest().getURI().getAuthority() + path; //  示例： localhost:6300/manage/xxxxxx

            // 检查是否有需要共享限流的路径配置
            List<String> apiCommunityShareLimitList = LocalCacheUtils.getDynamicRoutesDataCache().getApiCommunityShareLimitList();
            if (!CollectionUtils.isEmpty(apiCommunityShareLimitList)) {
                boolean matchFlag = apiCommunityShareLimitList.stream().anyMatch(p -> new PathPatternParser().parse(p).matches(PathContainer.parsePath(path)));
                if (matchFlag) {
                    finalStr = "apiCommunityShareLimitList_group"; // 唯一值即可
                }
            }

            return Mono.just(finalStr);
        };
    }

//    /**
//     * 限流方式：根据某个参数（如 用户ID）
//     * @return
//     */
//    @Bean
//    KeyResolver userIdKeyResolver(){
//        return exchange -> {
//            System.out.println(exchange.getRequest().getQueryParams().getFirst("userId"));
//            return Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
//        };
//    }
}
