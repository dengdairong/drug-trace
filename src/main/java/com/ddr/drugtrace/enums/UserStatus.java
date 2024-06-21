package com.ddr.drugtrace.enums;

import lombok.Data;

public enum UserStatus {
    ENABLE(1, "启用"),
    DISABLE(0, "禁用"),
    ;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 描述
     */
    private String mark;

    UserStatus(Integer status, String mark) {
        this.status = status;
        this.mark = mark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
