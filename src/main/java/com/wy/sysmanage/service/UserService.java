package com.wy.sysmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wy.sysmanage.entity.SysUser;


/**
 * 用户服务接口
 * @author wangyong
 */
public interface UserService {

    /**
     * 用户列表分页查询
     *
     * @param sysUser
     * @param page
     * @return
     */
    IPage<SysUser> selectPage(SysUser sysUser, IPage<SysUser> page);

    /**
     * 根据用户ID删除用户
     * @param userId
     */
    void deleteUserById(Long userId);

    /**
     * 修改用户
     * @param sysUser
     */
    void updateUser(SysUser sysUser);

    /**
     * 新增用户
     * @param sysUser
     */
    void saveUser(SysUser sysUser);
}
