package com.changgou.framework.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @作者:qipeiqing
 * @时间:2019/07/26 21:49
 */
@ControllerAdvice
public class BaseExceptionHandler {
    /**
     * 异常处理
     *
     * @ExceptionHandler: value = Exception.class:指定要处理的异常类型
     */
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        //e.getMessage()也可以自定义输出的String类型报错信息如：链接超时...
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }

}
