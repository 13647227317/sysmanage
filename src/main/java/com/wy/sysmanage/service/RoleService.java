package com.wy.sysmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.sysmanage.entity.SysRole;

/**
 * 角色管理服务接口
 * @author wangyong
 */
public interface RoleService {

    /**
     * 分页查询角色列表
     * @param page
     * @param sysRole
     * @return
     */
    Page<SysRole> selectPage(Page<SysRole> page, SysRole sysRole);

    /**
     * 增加或修改角色
     * @param sysRole
     */
    void saveOrUpdate(SysRole sysRole);

    /**
     * 根据角色id删除角色
     * @param roleId
     */
    void deleteById(Long roleId);

    /**
     * 根据id查询用户
     * @param roleId
     * @return
     */
    SysRole get(Long roleId);
}
