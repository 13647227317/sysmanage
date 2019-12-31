package com.wy.sysmanage.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wy.sysmanage.entity.SysUser;
import com.wy.sysmanage.mapper.SysUserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * shiro权限匹配与账号密码匹配
 * @author wangyong
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName= (String) authenticationToken.getPrincipal();
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUserAccount,userName);
        SysUser sysUser=sysUserMapper.selectOne(queryWrapper);
        if( null==sysUser ){
            throw new AuthenticationException();
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo( sysUser,sysUser.getUserPasswd(),
                ByteSource.Util.bytes(userName),getName());
        return authenticationInfo;
    }
}
