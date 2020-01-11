package com.wy.sysmanage.util;

import com.wy.sysmanage.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 * @author wangyong
 */
public class ShiroUtil {

    private ShiroUtil(){}

    /**
     * 查询当前登录用户
     * @return
     */
    public static SysUser getCurrentUser(){
        Subject subject= SecurityUtils.getSubject();
        return (SysUser) subject.getPrincipal();
    }
}
