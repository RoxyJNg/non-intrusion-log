package com.null01.nonintrusivelog.configuration;

import com.null01.nonintrusivelog.aspectj.NILogAnnotationAspect;
import com.null01.nonintrusivelog.aspectj.NILogDesignatorAspect;
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
    public NILogAnnotationAspect nILogAnnotationAspect(){
        return new NILogAnnotationAspect();
    }
    @Bean
    public NILogDesignatorAspect nILogDesignatorAspect(){
        return new NILogDesignatorAspect();
    }
}
