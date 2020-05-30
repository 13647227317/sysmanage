package com.wy.sysmanage.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import com.wy.sysmanage.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 * @author wangyong
 */
public class ShiroUtil {

    /** 生成密码时散列次数 */
    public static final int HASH_ITERATIONS=2;

    private ShiroUtil(){}

    /**
     * 查询当前登录用户
     * @return
     */
    public static SysUser getCurrentUser(){
        Subject subject= SecurityUtils.getSubject();
        return (SysUser) subject.getPrincipal();
    }

    /**  执行加密-采用SHA256和盐值加密,生成用户密码 **/
    public static String createPassword(String password, String salt) {
        return new SimpleHash(DigestAlgorithm.SHA256.getValue(), password, salt, HASH_ITERATIONS).toString();
    }
}
