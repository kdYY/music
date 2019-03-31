package com.ebook.common;

import com.ebook.util.config.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Payload<Result> exceptionHandle(Exception e){ // 处理方法参数的异常类型
        Result result = new Result();
        result.setMessage(e.getMessage());
        result.setObj("Exception");
        return new Payload(result);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Payload<Result> handle(RuntimeException e){
        Result result = new Result();
        result.setMessage(e.getMessage());
        result.setObj("runtimeException");
        return new Payload(result);
    }
}
