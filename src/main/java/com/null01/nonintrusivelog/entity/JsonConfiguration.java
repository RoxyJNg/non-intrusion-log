package com.null01.nonintrusivelog.entity;

import java.util.List;

/**
 * 配置实体
 */
public class JsonConfiguration {
    private List<Signature> before;
    private List<Signature> around;

    public List<Signature> getBefore() {
        return before;
    }

    public void setBefore(List<Signature> before) {
        this.before = before;
    }

    public List<Signature> getAround() {
        return around;
    }

    public void setAround(List<Signature> around) {
        this.around = around;
    }
}
