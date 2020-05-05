package com.null01.nonintrusivelog.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 */
public class JDKDynamicProxyDemo {
    public String className = "com.null01.nonintrusivelog.TestController";
    public String methodName = "hhh";

    public static void main(String[] args) throws Exception {
        JDKDynamicProxyDemo demo = new JDKDynamicProxyDemo();
        demo.run();
    }

    public void run() throws Exception{
        Object testController = Class.forName(className).newInstance();
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class[] interfaces = testController.getClass().getInterfaces();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(testController);
        Object self = Proxy.newProxyInstance(classLoader,interfaces,myInvocationHandler);
        Method method = self.getClass().getDeclaredMethod(methodName,"i".getClass(),int.class);
        method.invoke(self,"i",1);
    }

    class MyInvocationHandler implements InvocationHandler{
        private Object object;
        MyInvocationHandler(Object obj){
            object = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String argStr = "";
            for(Object arg:args){
                argStr += (arg+",");
            }
            System.err.println("----before---------："+ argStr.substring(0,argStr.length()-1));
            Object result = method.invoke(object,args);
            return result;
        }
    }
}
