package com.ddr.drugtrace.enums;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.role;

public enum UserRole {
    ADMIN(0, "管理员"),
    MANUFACTURER(1, "生产商"),
    DEALER(2, "经销商"),
    USER(3, "普通用户"),
    ;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 描述
     */
    private String mark;

    UserRole(Integer status, String mark) {
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

    public static UserRole getByName(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        return null;
    }
    public static UserRole getByStatus(Integer status) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.status.equals(status)) {
                return userRole;
            }
        }
        return null;
    }
}
