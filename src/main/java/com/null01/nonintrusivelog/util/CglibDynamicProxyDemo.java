package com.null01.nonintrusivelog.util;

import com.null01.nonintrusivelog.entity.Signature;
import com.null01.nonintrusivelog.resolver.JsonResolver;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * cglib动态代理（全村的希望）
 */
public class CglibDynamicProxyDemo {

    public List<Signature> beforeList = JsonResolver.getBefore();

    public CglibDynamicProxyDemo() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        CglibDynamicProxyDemo demo = new CglibDynamicProxyDemo();
        demo.run();
    }

    public void run()throws Exception{
        Signature before = beforeList.get(0);
        //创建Enhancer对象，类似于JDK动态代理的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置要继承的类
        enhancer.setSuperclass(Class.forName(before.getClazz()));
        enhancer.setCallback(new MyMethodInterceptor());

        //在编译前知道要调用的类和方法
//        TestController proxy1 = (TestController) enhancer.create();
//        proxy1.hhh("istr",22);
        //要是在运行时才知道，那就只能用反射
        Object proxy = enhancer.create();
        //处理方法签名
        String methodName = before.getMethod();
        List<Class> classList = new ArrayList<>();
        for(String arg:before.getArgs()){
            classList.add(Class.forName(arg));
        }
        Class[] classes = classList.toArray(new Class[classList.size()]);
        Object[] objs = new Object[]{"istr",22};

        Method method = proxy.getClass().getDeclaredMethod(methodName,classes);
        method.invoke(proxy,objs);
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
