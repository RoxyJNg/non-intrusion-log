package com.null01.nonintrusivelog.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    /**
     * 日志：环绕
     */
    @Pointcut("@annotation(com.null01.nonintrusivelog.annotation.NILogAround)")
    private void aroundPointCut(){}

    /**
     * 日志：后置
     */
    @Pointcut("@annotation(com.null01.nonintrusivelog.annotation.NILogAfter)")
    private void afterPointCut(){}

    @Before("beforePointCut()")
    public void before(){
        logger.info("---before:Test");
    }

    @Around("aroundPointCut()")
    public void around(ProceedingJoinPoint pjp) {
        logger.info("---around:before:Test");
        try {
            pjp.proceed();
        }catch (Throwable t){
            logger.error("---around:proceedThrowable:Test:"+t.getMessage());
        }
        logger.info("---around:after:Test");
    }

    @After("afterPointCut()")
    public void after(){
        logger.info("---after:Test");
    }
}
