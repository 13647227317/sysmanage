package com.wy.sysmanage.vo;

import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * 菜单信息Vo
 * @author wangyong
 */
@Data
@Builder
public class MenuTreeVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 子菜单列表
     */
    private List<MenuTreeVo> childrenList;

}
