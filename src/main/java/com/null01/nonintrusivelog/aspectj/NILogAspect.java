package com.null01.nonintrusivelog.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志切面
 */
@Aspect
public class NILogAspect {
    public static Logger logger = LoggerFactory.getLogger(NILogAspect.class);
    /**
     * 日志：前置
     */
    @Pointcut("@annotation(com.null01.nonintrusivelog.annotation.NILogBefore)")
    private void beforePointCut(){}

    @Before("beforePointCut()")
    public void before(){
        logger.info("---before:Test");
        System.err.println("---before:Test");
    }
}
