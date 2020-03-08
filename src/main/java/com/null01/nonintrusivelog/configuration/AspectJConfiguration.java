package com.null01.nonintrusivelog.configuration;

import com.null01.nonintrusivelog.aspectj.NILogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 切面配置
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectJConfiguration {
    @Bean
    public NILogAspect nILogAspect(){
        return new NILogAspect();
    }
}
