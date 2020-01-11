package com.wy.sysmanage.service.impl;

import com.wy.sysmanage.entity.SysMenu;
import com.wy.sysmanage.mapper.SysMenuMapper;
import com.wy.sysmanage.service.MenuService;
import com.wy.sysmanage.vo.MenuTreeVo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<MenuTreeVo> getUserMenuTree(Long userId) {
        List<SysMenu> menuList=sysMenuMapper.selectMenuByUserId(userId);
        return generatMenuTree(null,menuList);
    }


    /**
     * 递归方法构造菜单树
     * @param parentId
     * @param menuList
     * @return
     */
    private List<MenuTreeVo> generatMenuTree(Long parentId,List<SysMenu> menuList) {
        List<MenuTreeVo> menuTreeVoList=new ArrayList<>();
        if( CollectionUtils.isEmpty(menuList) ){
            return menuTreeVoList;
        }

        List<SysMenu> childrenList=menuList.stream().filter((e)->(isLongEqual(parentId,e.getParentId()))).collect(Collectors.toList());
        if( CollectionUtils.isEmpty(childrenList) ){
            return menuTreeVoList;
        }
        childrenList.forEach(t->{
                    MenuTreeVo mtv=MenuTreeVo
                            .builder()
                            .id(t.getId())
                            .menuCode(t.getMenuCode())
                            .menuName(t.getMenuName())
                            .childrenList(generatMenuTree(t.getId(),menuList))
                            .build();
                    menuTreeVoList.add(mtv);
                });
        return menuTreeVoList;
    }

    private boolean isLongEqual( Long long1,Long long2 ){
        if( null==long1 || null==long2 ){
            return (long1==null) && (long2==null) ;
        }
        return long1.longValue()==long2.longValue();
    }


    @Override
    public List<Long> getMenuListByRoleId(Long roleId) {
        List<Long> menuList=new ArrayList<>();
        List<SysMenu> sysMenuList=sysMenuMapper.selectMenuByRoleId(roleId);
        if ( !CollectionUtils.isEmpty(sysMenuList) ) {
            sysMenuList.forEach(t->menuList.add(t.getId()));
        }
        return menuList;
    }
}
