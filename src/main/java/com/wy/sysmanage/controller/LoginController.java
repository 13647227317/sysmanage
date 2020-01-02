package com.wy.sysmanage.controller;

import com.wy.sysmanage.constants.ResponseCodeEm;
import com.wy.sysmanage.util.ServerResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录流程前端控制器
 * @author wangyong
 */
@RestController
public class LoginController {

    @ApiOperation(value = "登录")
    @GetMapping("unLogin")
    public ServerResponse unLogin(){
        return ServerResponse.fail(ResponseCodeEm.NOT_LOGIN_ERROR);
    }

    @GetMapping("/login")
    public ServerResponse login(@RequestParam String userName, @RequestParam String userPasswd){
        UsernamePasswordToken token=new UsernamePasswordToken(userName,userPasswd);
        Subject subject= SecurityUtils.getSubject();
        subject.login(token);
        return ServerResponse.success(subject.getSession().getId().toString());
    }

    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    public ServerResponse logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return ServerResponse.success();
    }

    @RequiresRoles("admin")
    @GetMapping("/test")
    public ServerResponse test(){
        return ServerResponse.success();
    }
}
