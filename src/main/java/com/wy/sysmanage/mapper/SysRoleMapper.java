package com.wy.sysmanage.mapper;

import com.wy.sysmanage.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(Long userId);
}
