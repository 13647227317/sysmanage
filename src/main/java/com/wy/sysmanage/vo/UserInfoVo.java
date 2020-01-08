package com.wy.sysmanage.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 用户信息vo
 * @author wangyong
 */
@Data
@Builder
public class UserInfoVo {
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户菜单
     */
    private List<String> menuList;

}
