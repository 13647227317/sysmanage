package com.wy.sysmanage.service.impl;

import com.wy.sysmanage.entity.SysMenu;
import com.wy.sysmanage.mapper.SysMenuMapper;
import com.wy.sysmanage.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单服务实现类
 * @author wangyong
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> selectUserMenuList(Long userId) {
        return sysMenuMapper.selectMenuByUserId(userId);
    }
}
