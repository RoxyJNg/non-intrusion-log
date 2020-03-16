package com.null01.nonintrusivelog.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

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
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        Method method = methodSignature.getMethod();
        for (int i =0 ,len=parameterNames.length;i < len ;i++){
            System.out.println("参数名："+ parameterNames[i] + " = " +args[i]);
        }
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
