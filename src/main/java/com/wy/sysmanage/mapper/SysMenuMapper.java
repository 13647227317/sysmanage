package com.wy.sysmanage.mapper;

import com.wy.sysmanage.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangyong
 * @since 2019-12-30
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色ID查询角色对应菜单列表
     * @param roleId
     * @return
     */
    List<SysMenu> selectMenuByRoleId(Long roleId);

}
