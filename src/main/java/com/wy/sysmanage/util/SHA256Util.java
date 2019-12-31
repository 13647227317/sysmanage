package com.wy.sysmanage.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * SHA256加密工具
 * @author wangyong
 */
public class SHA256Util {

    /**  加密算法 **/
    public final static String HASH_ALGORITHM_NAME = "SHA-256";
    /**  循环次数 **/
    public final static int HASH_ITERATIONS = 2;

    private SHA256Util(){}

    /**  执行加密-采用SHA256和盐值加密 **/
    public static String sha256(String password, String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
    }

}
