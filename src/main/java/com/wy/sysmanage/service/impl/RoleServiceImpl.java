package com.wy.sysmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.sysmanage.entity.SysRole;
import com.wy.sysmanage.entity.SysRoleMenu;
import com.wy.sysmanage.mapper.SysRoleMapper;
import com.wy.sysmanage.mapper.SysRoleMenuMapper;
import com.wy.sysmanage.service.RoleService;
import com.wy.sysmanage.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理服务接口实现
 * @author wangyong
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public Page<SysRole> selectPage(Page page, SysRole sysRole) {
        if( null==page ){
            page=new Page();
        }
        LambdaQueryWrapper<SysRole> queryWrapper=new QueryWrapper<SysRole>().lambda();
        if( null!=sysRole ){
            if(!StringUtils.isEmpty(sysRole.getRoleCode())){
                queryWrapper.like(SysRole::getRoleCode,sysRole.getRoleCode());
            }
            if(!StringUtils.isEmpty(sysRole.getRoleName())){
                queryWrapper.like(SysRole::getRoleName,sysRole.getRoleName());
            }
        }
        queryWrapper.orderByDesc(SysRole::getUpdateTime);
        return sysRoleMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void saveOrUpdate(SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        if( null==sysRole.getId() ){
            sysRoleMapper.insert(sysRole);

        }else{
            sysRoleMapper.updateById(sysRole);
            LambdaQueryWrapper<SysRoleMenu> deleteWrapper=new QueryWrapper<SysRoleMenu>().lambda();
            sysRoleMenuMapper.delete(deleteWrapper.eq(SysRoleMenu::getRoleId,sysRole.getId()));
        }
        List<SysRoleMenu> roleMenuList=new ArrayList<>();
        String menuIds=sysRole.getMenuIds();
        if( null!=menuIds ){
            String[] menuIdArray=menuIds.split(",");
            for( String menuIdStr:menuIdArray ){
                SysRoleMenu sysRoleMenu=SysRoleMenu
                        .builder()
                        .roleId(sysRole.getId())
                        .menuId(Long.parseLong(menuIdStr))
                        .build();
                roleMenuList.add(sysRoleMenu);
            }
            sysRoleMenuService.saveOrUpdateBatch(roleMenuList);
        }
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    @Override
    public SysRole get(Long roleId) {
        return sysRoleMapper.selectById(roleId);
    }

    @Override
    public SysRole getRoleByCode(String roleCode) {
        LambdaQueryWrapper<SysRole> queryWrapper=new QueryWrapper<SysRole>().lambda();
        queryWrapper.eq(SysRole::getRoleCode,roleCode);
        return sysRoleMapper.selectOne(queryWrapper);
    }

    @Override
    public List<SysRole> selectList() {
        LambdaQueryWrapper<SysRole> queryWrapper=new QueryWrapper<SysRole>().lambda();
        return sysRoleMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysRole> seleRoleListByUserId(Long userId) {
        return sysRoleMapper.selectRoleByUserId(userId);
    }
}
