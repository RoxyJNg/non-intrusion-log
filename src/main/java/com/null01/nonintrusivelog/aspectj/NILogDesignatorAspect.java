package com.null01.nonintrusivelog.aspectj;

import com.null01.nonintrusivelog.resolver.JsonResolver;
import com.null01.nonintrusivelog.util.LogAction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 日志切面（指示器表达式型）
 */
@Aspect
public class NILogDesignatorAspect {

    final String str = "execution(* com.null01.nonintrusivelog.TestController.hhh(..))";
    //注解参数必须要是解析期常量，唉
//    final String beforeValue = JsonResolver.getBefore();

    public NILogDesignatorAspect() throws Exception {
    }

    @Before(str)
    public void testBefore(JoinPoint joinPoint){
        LogAction.before(joinPoint);
    }

    @Around(str)
    public void testAround(ProceedingJoinPoint pjp) throws Throwable{
        LogAction.around(pjp);
    }

    @AfterThrowing(pointcut = str,throwing="ex")
    public void testThrowing(JoinPoint joinPoint,Exception ex) throws Exception{
        LogAction.throwing(joinPoint,ex);
    }
}
