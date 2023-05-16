package com.oik.api.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_role")
@Accessors(chain = true)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 角色
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 描述
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建人
     */
    @TableField(value = "created_by",fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
}
