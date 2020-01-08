package com.wy.sysmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.sysmanage.entity.SysUser;
import com.wy.sysmanage.exception.BizException;
import com.wy.sysmanage.mapper.SysMenuMapper;
import com.wy.sysmanage.mapper.SysUserMapper;
import com.wy.sysmanage.service.UserService;
import com.wy.sysmanage.util.SHA256Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 用户服务实现类
 * @author wangyong
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${default.password}")
    private String defaultPassword;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;


    @Override
    public IPage<SysUser> selectPage(SysUser sysUser, IPage<SysUser> page) {
        if( null==page ) {
            page = new Page<SysUser>(1,10);
        }
        LambdaQueryWrapper<SysUser> queryWrapper=new QueryWrapper<SysUser>().lambda();
        if( null!=sysUser ){
            if( !StringUtils.isEmpty(sysUser.getUserAccount()) ){
                queryWrapper.like(SysUser::getUserAccount,sysUser.getUserAccount());
            }
            if(!StringUtils.isEmpty(sysUser.getUserName())){
                queryWrapper.like(SysUser::getUserName,sysUser.getUserName());
            }
        }
        queryWrapper.orderByDesc(SysUser::getUpdateTime);
        return sysUserMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void deleteUserById(Long userId) {
        sysUserMapper.deleteById(userId);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        SysUser oldUser=sysUserMapper.selectById(sysUser.getId());
        if( null==oldUser ){
            throw new BizException("用户不存在");
        }

        oldUser.setUpdateTime(LocalDateTime.now());
        if( !StringUtils.isEmpty(sysUser.getUserName()) ){
            oldUser.setUserName(sysUser.getUserName());
        }
        if( !StringUtils.isEmpty(sysUser.getMobile()) ){
            oldUser.setMobile(sysUser.getMobile());
        }
        sysUserMapper.updateById(oldUser);
    }

    @Override
    public void saveUser(SysUser sysUser) {
        sysUser.setUpdateTime(LocalDateTime.now());
        sysUser.setUserPasswd(SHA256Util.sha256(defaultPassword,sysUser.getUserAccount()));
        sysUserMapper.insert(sysUser);
    }
}
