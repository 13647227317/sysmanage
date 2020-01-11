package com.wy.sysmanage.controller;

import com.wy.sysmanage.entity.SysUser;
import com.wy.sysmanage.service.MenuService;
import com.wy.sysmanage.util.ServerResponse;
import com.wy.sysmanage.util.ShiroUtil;
import com.wy.sysmanage.vo.MenuTreeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单模块前端控制器
 * @author wangyong
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @ApiOperation("查询菜单树")
    @GetMapping("/getMenuTree")
    public ServerResponse getMenuTree(){
        SysUser currentUser= ShiroUtil.getCurrentUser();
        List<MenuTreeVo> list=menuService.getUserMenuTree(currentUser.getId());
        return ServerResponse.success(list);
    }

    @ApiOperation("查询角色对应的菜单id列表")
    @GetMapping("/getMenuListByRoleId/{roleId}")
    public ServerResponse getMenuListByRoleId(@PathVariable("roleId") Long roleId){
        List<Long> menuList=menuService.getMenuListByRoleId(roleId);
        return ServerResponse.success(menuList);
    }

}
