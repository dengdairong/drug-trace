package com.ddr.drugtrace.conf;

import com.ddr.drugtrace.util.ResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@ResponseBody
@Log4j2
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
    }
    @ExceptionHandler
    public ResponseDto handleException(Exception e) {
        if (e instanceof AppException) {
            log.error("AppException:", e);
            return ResponseDto.fail((AppException) e);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            log.error("MethodArgumentNotValidException:", e.getMessage());
            return ResponseDto.fail(ex.getBindingResult().getFieldError().getDefaultMessage());
        } else {
            log.error("系统异常:", e);
            return ResponseDto.fail("系统异常请稍后重试!");
        }
    }
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseDto handlerException(){
        return ResponseDto.fail("无权限访问接口!");
    }
}
