package com.wy.sysmanage.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * 菜单信息Vo
 * @author wangyong
 */
@Data
public class SysMenuVo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 菜单状态(0-禁用，1-启用)
     */
    private Integer status;

    /**
     * 菜单顺序
     */
    private Integer menuOrder;

    /**
     * 上级菜单ID
     */
    private Long parentId;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 菜单对应页面
     */
    private String menuView;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 子菜单列表
     */
    private List<SysMenuVo> childrenList;

}
