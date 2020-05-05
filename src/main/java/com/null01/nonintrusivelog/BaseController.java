package com.null01.nonintrusivelog;

/**
 * jdk动态代理用，由于jdk动态代理是通过实现同一个接口的同名方法，所以接口一定要有代理目标方法的声明
 */
public interface BaseController {
    String hhh(String i,int e) throws Exception;
    String hhh2() throws Exception;
}
