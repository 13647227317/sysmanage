package com.wy.sysmanage.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wy.sysmanage.constants.ResponseCodeEm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应信息主题
 * @author wangyong
 */
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private static final long serialVersionUID = -3761558872476590165L;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应内容
     */
    private T data;

    public static ServerResponse success() {
        return build(ResponseCodeEm.SUCCESS);
    }

    public static ServerResponse success(Object data) {
        return new ServerResponse(ResponseCodeEm.SUCCESS.value(),ResponseCodeEm.SUCCESS.msg(),data);
    }

    public static ServerResponse fail(ResponseCodeEm responseCodeEm) {
        return build(responseCodeEm);
    }

    public static ServerResponse fail(String code,String message) {
        return new ServerResponse(code,message,null);
    }

    public static ServerResponse build(ResponseCodeEm rce) {
        return new ServerResponse(rce.value(),rce.msg(),null);
    }

}
