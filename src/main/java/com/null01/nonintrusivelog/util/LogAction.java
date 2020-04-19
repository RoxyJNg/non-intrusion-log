package com.null01.nonintrusivelog.util;

import com.null01.nonintrusivelog.aspectj.NILogAnnotationAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 日志核心功能逻辑
 */
public class LogAction {
    public static Logger logger = LoggerFactory.getLogger(NILogAnnotationAspect.class);

    /**
     * 前置记录
     */
    public static void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        String[] parameterNames = methodSignature.getParameterNames();
        String paramStr = "";
        for (int i =0 ,len=parameterNames.length;i < len ;i++){
            paramStr += parameterNames[i] + " = " +args[i]+";";
        }
        logger.info("[NILogBefore] <METHOD NAME>:"+methodName+" <PARAMETERS>:"+paramStr);
    }

    /**
     * 环绕记录
     */
    public static void around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        String[] parameterNames = methodSignature.getParameterNames();
        String paramStr = "";
        for (int i =0 ,len=parameterNames.length;i < len ;i++){
            paramStr += parameterNames[i] + " = " +args[i]+";";
        }
        logger.info("[NILogAround:before] <METHOD NAME>:"+methodName+" <PARAMETERS>:"+paramStr);
        Object result =  pjp.proceed();
        logger.info("[NILogAround:after] <RESULT>:"+result);
    }

    /**
     * 异常记录
     */
    public static void throwing(JoinPoint joinPoint,Exception ex) throws Exception{
        logger.error("[NILogThrowable] <MESSAGE>:"+ex.getMessage());
    }
}
