package com.wy.sysmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.sysmanage.entity.SysRole;
import com.wy.sysmanage.mapper.SysRoleMapper;
import com.wy.sysmanage.service.RoleService;
import com.wy.sysmanage.util.ServerResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 角色管理服务接口实现
 * @author wangyong
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public Page<SysRole> selectPage(Page<SysRole> page, SysRole sysRole) {
        return sysRoleMapper.selectPage(page,new QueryWrapper<>(sysRole));
    }

    @Override
    public void saveOrUpdate(SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        if( null==sysRole.getId() ){
            sysRoleMapper.insert(sysRole);
        }else{
            sysRoleMapper.updateById(sysRole);
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
}
