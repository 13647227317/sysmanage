package com.wy.sysmanage.controller;

import com.wy.sysmanage.constants.ResponseCodeEm;
import com.wy.sysmanage.util.ServerResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 自定义异常处理器
 * @author wangyong
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ServerResponse defaultExceptionHandler(Throwable ex){

        if( ex instanceof IncorrectCredentialsException){
            return ServerResponse.fail(ResponseCodeEm.PASSWORD_ERROR);
        }

        if( ex instanceof AuthenticationException){
            return ServerResponse.fail(ResponseCodeEm.USER_NOT_EXIST);
        }
        logger.error("",ex);
        return ServerResponse.fail(ResponseCodeEm.INTERNAL_SERVER_ERROR);
    }

}
