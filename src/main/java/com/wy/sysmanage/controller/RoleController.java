package com.wy.sysmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.sysmanage.entity.SysRole;
import com.wy.sysmanage.service.RoleService;
import com.wy.sysmanage.util.ServerResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理控制器
 * @author wangyong
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation("角色列表分页查询")
    @GetMapping("/selectPage")
    public ServerResponse<Page<SysRole>> selectPage(Page page,SysRole sysRole){
        Page pageResult=roleService.selectPage(page,sysRole);
        return ServerResponse.success(pageResult);
    }

    @ApiOperation("角色列表查询")
    @GetMapping("/selectList")
    public ServerResponse<List<SysRole>> selectList(){
        List<SysRole> list=roleService.selectList();
        return ServerResponse.success(list);
    }

    @ApiOperation("查询用户所具有的角色列表")
    @GetMapping("/selectUserRoleList/{userId}")
    public ServerResponse<List<SysRole>> selectUserRoleList(@PathVariable("userId") Long userId){
        List<SysRole> list=roleService.seleRoleListByUserId(userId);
        return ServerResponse.success(list);
    }

    @ApiOperation("根据ID查询角色")
    @GetMapping("/get/{id}")
    public ServerResponse<SysRole> get(@PathVariable(value="id") Long roleId){
        SysRole sysRole=roleService.get(roleId);
        return ServerResponse.success(sysRole);
    }

    @ApiOperation("根据编码查询角色")
    @GetMapping("/getRoleByCode/{roleCode}")
    public ServerResponse<SysRole> getRoleByCode(@PathVariable(value="roleCode") String roleCode){
        SysRole sysRole=roleService.getRoleByCode(roleCode);
        return ServerResponse.success(sysRole);
    }

    @ApiOperation("角色新增")
    @PostMapping("/save")
    public ServerResponse save(@RequestBody SysRole sysRole){
        roleService.saveOrUpdate(sysRole);
        return ServerResponse.success();
    }

    @ApiOperation("角色修改")
    @PostMapping("/update")
    public ServerResponse updateRole(@RequestBody SysRole sysRole){
        roleService.saveOrUpdate(sysRole);
        return ServerResponse.success();
    }

    @ApiOperation("角色删除")
    @DeleteMapping("/delete/{id}")
    public ServerResponse delete(@PathVariable(value = "id") Long roleId){
        roleService.deleteById(roleId);
        return ServerResponse.success();
    }
}
