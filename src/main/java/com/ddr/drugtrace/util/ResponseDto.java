package com.ddr.drugtrace.util;

import com.ddr.drugtrace.conf.AppException;
import lombok.Data;

@Data
public class ResponseDto<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(0, "success", data);
    }

    public static <T> ResponseDto<T> fail(String error) {
        return new ResponseDto<>(1, error, null);
    }

    public static <T> ResponseDto<T> fail(AppException e) {
        return new ResponseDto<>(e.getCode(), e.getMsg(), null);
    }
}
