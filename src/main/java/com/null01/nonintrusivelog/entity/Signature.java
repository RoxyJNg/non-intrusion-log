package com.null01.nonintrusivelog.entity;

import java.util.List;

/**
 * @author:WuSJ
 * @date:2020/05/11
 */
public class Signature {
    private String clazz;
    private String method;
    private List<String> args;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
