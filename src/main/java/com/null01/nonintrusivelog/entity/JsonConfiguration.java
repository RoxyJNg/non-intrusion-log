package com.null01.nonintrusivelog.entity;

import java.util.List;

/**
 * 配置实体
 */
public class JsonConfiguration {
    private List<String> before;
    private List<String> around;

    public List<String> getBefore() {
        return before;
    }

    public void setBefore(List<String> before) {
        this.before = before;
    }

    public List<String> getAround() {
        return around;
    }

    public void setAround(List<String> around) {
        this.around = around;
    }
}
