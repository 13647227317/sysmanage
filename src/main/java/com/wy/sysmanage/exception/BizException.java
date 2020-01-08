package com.wy.sysmanage.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务异常
 * @author wangyong
 */
@NoArgsConstructor
public class BizException extends RuntimeException {
    /**
     * exception code
     */
    private String code;

    /**
     * exception message
     */
    private String msg;

    public BizException(String msg) {
        super(msg);
    }
}
