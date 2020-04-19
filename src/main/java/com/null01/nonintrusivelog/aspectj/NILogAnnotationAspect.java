package com.null01.nonintrusivelog.aspectj;

import com.null01.nonintrusivelog.util.LogAction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志切面（注解型）
 */
@Aspect
public class NILogAnnotationAspect {
    public static Logger logger = LoggerFactory.getLogger(NILogAnnotationAspect.class);
    /**
     * 日志：前置
     */
    @Pointcut("@annotation(com.null01.nonintrusivelog.annotation.NILogBefore)")
    private void beforePointCut(){
    }

    /**
     * 日志：环绕
     */
    @Pointcut("@annotation(com.null01.nonintrusivelog.annotation.NILogAround)")
    private void aroundPointCut(){}


    @Before("beforePointCut()")
    public void before(JoinPoint joinPoint){
        LogAction.before(joinPoint);
    }

    @Around("aroundPointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        LogAction.around(pjp);
    }

    /**
     * 异常记录
     */
    @AfterThrowing(pointcut ="beforePointCut()", throwing="ex")
    public void beforePointCutThrowing(JoinPoint joinPoint,Exception ex) throws Exception{
        LogAction.throwing(joinPoint, ex);
    }

    @AfterThrowing(pointcut ="aroundPointCut()", throwing="ex")
    public void aroundPointCutThrowing(JoinPoint joinPoint,Exception ex) throws Exception{
        LogAction.throwing(joinPoint, ex);
    }
}
