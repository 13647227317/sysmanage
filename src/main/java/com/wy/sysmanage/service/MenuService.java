package com.wy.sysmanage.service;

import com.wy.sysmanage.entity.SysMenu;
import com.wy.sysmanage.vo.MenuTreeVo;

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

    /**
     * 获取用户菜单树
     * @param userId
     * @return
     */
    List<MenuTreeVo> getUserMenuTree(Long userId);

    /**
     * 查询角色已具有权限的菜单id列表
     * @param roleId
     * @return
     */
    List<Long> getMenuListByRoleId(Long roleId);
}

