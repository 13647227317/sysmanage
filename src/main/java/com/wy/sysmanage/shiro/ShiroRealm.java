package com.wy.sysmanage.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wy.sysmanage.entity.SysMenu;
import com.wy.sysmanage.entity.SysRole;
import com.wy.sysmanage.entity.SysUser;
import com.wy.sysmanage.mapper.SysMenuMapper;
import com.wy.sysmanage.mapper.SysRoleMapper;
import com.wy.sysmanage.mapper.SysUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro权限匹配与账号密码匹配
 * @author wangyong
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        Set<String> roleSet=new HashSet<>();
        Set<String> permSet=new HashSet<>();
        SysUser sysUser= (SysUser) principalCollection.getPrimaryPrincipal();
        List<SysRole> userRoleList=sysRoleMapper.selectRoleByUserId(sysUser.getId());
        if( !CollectionUtils.isEmpty(userRoleList) ){
            userRoleList.forEach(role->{
                roleSet.add(role.getRoleCode());
                List<SysMenu> menuList=sysMenuMapper.selectMenuByRoleId(role.getId());
                if( !CollectionUtils.isEmpty(menuList) ){
                    menuList.forEach(menu->permSet.add(menu.getMenuCode()));
                }
            });
        }
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permSet);
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userAccount= (String) authenticationToken.getPrincipal();
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUserAccount,userAccount);
        SysUser sysUser=sysUserMapper.selectOne(queryWrapper);
        if( null==sysUser ){
            throw new AuthenticationException();
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo( sysUser,sysUser.getUserPasswd(),
                ByteSource.Util.bytes(userAccount),getName());
        return authenticationInfo;
    }
}
