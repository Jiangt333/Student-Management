package com.example.authservice.config;

import com.example.authservice.interceptors.CORSInterceptor;
import com.example.authservice.interceptors.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private JWTInterceptor jWTInterceptor;

    @Autowired
    private CORSInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jWTInterceptor)
                .addPathPatterns("/user/**", "/score/**", "/mail/**")
                .excludePathPatterns("/**/login");

        registry.addInterceptor(corsInterceptor)
                .addPathPatterns("/**");
    }
}