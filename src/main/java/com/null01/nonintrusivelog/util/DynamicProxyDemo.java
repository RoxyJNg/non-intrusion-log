package com.null01.nonintrusivelog.util;

import com.null01.nonintrusivelog.BaseController;

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
        Object testController = Class.forName(className).newInstance();
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class[] interfaces = testController.getClass().getInterfaces();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(testController);
        Object self = Proxy.newProxyInstance(classLoader,interfaces,myInvocationHandler);
        Method method = self.getClass().getDeclaredMethod(methodName);
        method.invoke(self);
    }

    class MyInvocationHandler implements InvocationHandler{
        private Object object;
        MyInvocationHandler(Object obj){
            object = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.err.println("----before---------");
            Object result = method.invoke(object,args);
            return result;
        }
    }
}
