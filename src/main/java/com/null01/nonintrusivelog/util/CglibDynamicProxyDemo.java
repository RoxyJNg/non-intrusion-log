package com.null01.nonintrusivelog.util;

import com.null01.nonintrusivelog.TestController;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 */
public class CglibDynamicProxyDemo {
    public String className = "com.null01.nonintrusivelog.TestController";
    public String methodName = "hhh";

    public static void main(String[] args) throws Exception {
        CglibDynamicProxyDemo demo = new CglibDynamicProxyDemo();
        demo.run();
    }

    public void run()throws Exception{
        //创建Enhancer对象，类似于JDK动态代理的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置要继承的类
        enhancer.setSuperclass(TestController.class);
        enhancer.setCallback(new MyMethodInterceptor());

        //在编译前知道要调用的类和方法
        TestController proxy1 = (TestController) enhancer.create();
        proxy1.hhh("istr",22);
        //要是在运行时才知道，那就只能用反射
        Object proxy = enhancer.create();
        Method method = proxy.getClass().getDeclaredMethod(methodName,String.class,int.class);
        method.invoke(proxy,"istr",22);
    }

    public class MyMethodInterceptor implements MethodInterceptor{
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            String argStr = "";
            for(Object arg:args){
                argStr += (arg+",");
            }
            System.err.println("----before---------："+ argStr.substring(0,argStr.length()-1));
            //注意这里的方法调用，不是用反射
            Object object = proxy.invokeSuper(obj, args);
            return object;
        }
    }
}
