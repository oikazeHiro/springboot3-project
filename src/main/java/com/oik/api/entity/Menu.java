package com.oik.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private String id;

    /**
     * 父id
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 类型;0菜单1分类2路由3按钮
     */
    @TableField("`TYPE`")
    private Short type;

    /**
     * 等级
     */
    @TableField("`LEVEL`")
    private Short level;

    /**
     * 名称
     */
    @TableField("`NAME`")
    private String name;

    /**
     * 路由
     */
    @TableField("`PATH`")
    private String path;

    /**
     * 组件路径
     */
    @TableField("`COMPONENT`")
    private String component;

    /**
     * 权限
     */
    @TableField("PERMS")
    private String perms;

    /**
     * 图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 排序
     */
    @TableField("ORDER_NUM")
    private Integer orderNum;

    /**
     * 状态;1 true 0 false
     */
    @TableField("`STATUS`")
    private Short status;

    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;
}
