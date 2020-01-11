package com.wy.sysmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.sysmanage.constants.ResponseCodeEm;
import com.wy.sysmanage.entity.SysMenu;
import com.wy.sysmanage.entity.SysUser;
import com.wy.sysmanage.service.MenuService;
import com.wy.sysmanage.service.UserService;
import com.wy.sysmanage.util.ServerResponse;
import com.wy.sysmanage.vo.UserInfoVo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理前端控制器
 * @author wangyong
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @ApiOperation(value = "登录")
    @GetMapping("unLogin")
    public ServerResponse unLogin(){
        return ServerResponse.fail(ResponseCodeEm.NOT_LOGIN_ERROR);
    }

    @PostMapping("/login")
    public ServerResponse login(@RequestBody SysUser sysUser){
        UsernamePasswordToken token=new UsernamePasswordToken(sysUser.getUserAccount(),sysUser.getUserPasswd());
        Subject subject= SecurityUtils.getSubject();
        subject.login(token);
        return ServerResponse.success(subject.getSession().getId().toString());
    }

    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    public ServerResponse logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return ServerResponse.success();
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/info")
    public ServerResponse<UserInfoVo> getInfo(){
        Subject subject=SecurityUtils.getSubject();
        SysUser sysUser= (SysUser) subject.getPrincipal();
        List<String> menuCodeList=new ArrayList<>();
        List<SysMenu> menuList=menuService.selectUserMenuList(sysUser.getId());
        if( !CollectionUtils.isEmpty(menuList) ){
            menuList.forEach(t->menuCodeList.add(t.getMenuCode()));
        }
        UserInfoVo userInfoVo=UserInfoVo.builder()
                .userName(sysUser.getUserName())
                .menuList(menuCodeList)
                .build();
        return ServerResponse.success(userInfoVo);
    }

    @ApiOperation(value="用户分页查询")
    @GetMapping("/selectPage")
    public ServerResponse<IPage<SysUser>> selectPage(SysUser sysUser, Page page){
        IPage<SysUser> userPage=userService.selectPage(sysUser,page);
        return ServerResponse.success(userPage);
    }

    @ApiOperation(value = "用户新增")
    @PostMapping("/save")
    public ServerResponse saveUser(@RequestBody SysUser sysUser){
        userService.saveUser(sysUser);
        return ServerResponse.success();
    }

    @ApiOperation(value = "用户修改")
    @PostMapping("/update")
    public ServerResponse updateUser(@RequestBody SysUser sysUser ){
        userService.updateUser(sysUser);
        return ServerResponse.success();
    }

    @ApiOperation(value = "用户删除")
    @DeleteMapping("/delete/{userId}")
    public ServerResponse deleteUserById(@PathVariable("userId") Long userId ){
        userService.deleteUserById(userId);
        return ServerResponse.success();
    }

    @ApiOperation(value = "密码重置")
    @PostMapping("/resetPassword/{userId}")
    public ServerResponse resetPassword(@PathVariable("userId") Long userId){
        userService.resetPassword(userId);
        return ServerResponse.success();
    }

    @ApiOperation(value = "根据用户名查询用户")
    @GetMapping("/getUserByAccount/{userAccount}")
    public ServerResponse<SysUser> getUserByAccount(@PathVariable("userAccount") String userAccount){
        SysUser result=null;
        SysUser sysUser=new SysUser();
        sysUser.setUserAccount(userAccount);
        List<SysUser> list=userService.selectList(sysUser);
        if( !CollectionUtils.isEmpty(list) ){
            result=list.get(0);
        }
        return ServerResponse.success(result);
    }
}
