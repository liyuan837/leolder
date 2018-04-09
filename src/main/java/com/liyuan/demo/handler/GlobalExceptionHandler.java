package com.liyuan.demo.handler;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 16:04 2018/2/8
 * @Modified By:
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    private Map<String,Object> exceptionHandler(HttpServletRequest request, Exception exception){
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",false);
        modelMap.put("errMsg",exception.getMessage());
        return modelMap;
    }
}
