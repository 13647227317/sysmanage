package com.wy.sysmanage.constants;

/**
 * 响应码枚举
 * @author wangyong
 */

public enum ResponseCodeEm {
    /**
     * 成功
     */
    SUCCESS("200","成功"),

    /**
     * 未登录
     */
    NOT_LOGIN_ERROR("201","未登录"),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR("500","服务器内部错误"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("401","用户不存在"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR("402","密码错误"),

    /**
     * 鉴权失败
     */
    INSUFFICIENT_AUTHORITY("403","权限不足");

    private String value;

    private String msg;

    ResponseCodeEm(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String value(){
        return this.value;
    }

    public String msg(){
        return this.msg;
    }
}
