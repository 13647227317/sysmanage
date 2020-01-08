package com.wy.sysmanage.service;

import com.wy.sysmanage.entity.SysMenu;

import java.util.List;

/**
 * 菜单服务接口
 * @author wangyong
 */
public interface MenuService {
    /**
     * 查询用户菜单列表
     * @param userId
     * @return
     */
    List<SysMenu> selectUserMenuList(Long userId);
}

