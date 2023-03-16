package com.briup.cms.exception;

import com.briup.cms.util.Result;
import com.briup.cms.util.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
//@ControllerAdvice + @ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        Result result = null;
        if (e instanceof ServiceException) {
            result = Result.failure(((ServiceException) e).getResultCode());
        }else {
            result = Result.failure(ResultCode.ERROR, "服务器意外错误：" + e.getMessage());
        }
        return result;
    }
}
