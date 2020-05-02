package com.null01.nonintrusivelog.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public String className = "com.null01.nonintrusivelog.TestController";
    public String methodName = "hhh2";

    public static void main(String[] args) throws Exception {
        DynamicProxyDemo demo = new DynamicProxyDemo();
        demo.run();
    }

    public void run() throws Exception{
        Object obj = Class.forName(className).newInstance();
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class[] classes = new Class[]{obj.getClass()};
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(obj);
        Object self = Proxy.newProxyInstance(classLoader,classes,myInvocationHandler);
        Method method = self.getClass().getDeclaredMethod(methodName);
        method.invoke("hah");
    }

    class MyInvocationHandler implements InvocationHandler{
        private Object object;
        MyInvocationHandler(Object obj){
            object = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.err.println("----before---------");
            method.invoke(object);
            return null;
        }
    }
}
