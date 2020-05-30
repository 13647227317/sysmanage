package com.wy.sysmanage.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.wy.sysmanage.constants.ResponseCodeEm;
import com.wy.sysmanage.exception.BizException;
import com.wy.sysmanage.util.ServerResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常处理器
 * @author wangyong
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Log log= LogFactory.get();

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ServerResponse defaultExceptionHandler(Throwable ex){

        if( ex instanceof IncorrectCredentialsException){
            return ServerResponse.fail(ResponseCodeEm.PASSWORD_ERROR);
        }

        if( ex instanceof AuthenticationException){
            return ServerResponse.fail(ResponseCodeEm.USER_NOT_EXIST);
        }
        if( ex instanceof UnauthorizedException){
            return ServerResponse.fail(ResponseCodeEm.INSUFFICIENT_AUTHORITY);
        }
        if( ex instanceof BizException){
            return ServerResponse.fail("401",ex.getMessage());
        }
        log.error("",ex);
        return ServerResponse.fail(ResponseCodeEm.INTERNAL_SERVER_ERROR);
    }

}
