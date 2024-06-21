package com.ddr.drugtrace.conf;

import lombok.Data;

@Data
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    public AppException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
