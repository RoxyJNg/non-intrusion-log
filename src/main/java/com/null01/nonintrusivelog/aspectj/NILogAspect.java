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


    @Before("beforePointCut()")
    public void before(JoinPoint joinPoint){
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

    @Around("aroundPointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
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
    @AfterThrowing(pointcut ="beforePointCut()", throwing="ex")
    public void beforePointCutThrowing(JoinPoint joinPoint,Exception ex) throws Exception{
        doAfterThrowing(joinPoint,ex);
    }

    @AfterThrowing(pointcut ="aroundPointCut()", throwing="ex")
    public void aroundPointCutThrowing(JoinPoint joinPoint,Exception ex) throws Exception{
        doAfterThrowing(joinPoint,ex);
    }

    private void doAfterThrowing(JoinPoint joinPoint,Exception ex) throws Exception{
        logger.error("[NILogThrowable] <MESSAGE>:"+ex.getMessage());
    }
}
