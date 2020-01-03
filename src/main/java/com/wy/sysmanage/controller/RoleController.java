package com.wy.sysmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.sysmanage.entity.SysRole;
import com.wy.sysmanage.service.RoleService;
import com.wy.sysmanage.util.ServerResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色管理控制器
 * @author wangyong
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/selectPage")
    public ServerResponse<Page<SysRole>> selectPage(Page<SysRole> page,SysRole sysRole){
        Page pageResult=roleService.selectPage(page,sysRole);
        return ServerResponse.success(pageResult);
    }

    @GetMapping("/get/{id}")
    public ServerResponse<SysRole> get(@PathVariable(value="id") Long roleId){
        SysRole sysRole=roleService.get(roleId);
        return ServerResponse.success(sysRole);
    }

    @PostMapping("/saveOrUpdate")
    public ServerResponse saveOrUpdate(SysRole sysRole){
        roleService.saveOrUpdate(sysRole);
        return ServerResponse.success();
    }

    @DeleteMapping("/delete/{id}")
    public ServerResponse delete(@PathVariable(value = "id") Long roleId){
        roleService.deleteById(roleId);
        return ServerResponse.success();
    }
}
