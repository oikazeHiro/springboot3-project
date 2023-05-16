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
    @TableId("id")
    private String id;

    /**
     * 父id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 类型;0菜单1按钮
     */
    @TableField("type")
    private Short type;

    /**
     * 等级
     */
    @TableField("level")
    private Short level;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 路由
     */
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 权限
     */
    @TableField("perms")
    private String perms;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 状态;1 true 0 false
     */
    @TableField("status")
    private Short status;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
