package com.wang.server.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Value("${management.endpoints.web.base-path}")
    private String basePath;
    @Value("${spring.security.user.roles}")
    private String roleName;

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange().pathMatchers(basePath + "/**").hasRole(roleName).anyExchange().permitAll().and().csrf()
                .and().httpBasic().and().csrf().disable()
        .logout().logoutUrl("/9QZ2sg37yjKxm5RE0tIJ"); // 解决问题：请求/logout和框架冲突导致框架302
        return http.build();
    }

}
