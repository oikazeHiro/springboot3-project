package com.oik.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色绑定
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * USER_ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * ROLE_ID
     */
    @TableField("role_id")
    private String roleId;
}
