package com.oik.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import lombok.experimental.Accessors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author oik
 * @since 2023-05-02
 */
@Getter
@Setter
@TableName("sys_user")
@Accessors(chain = true)
public class User implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("id")
    private String id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField("password")
    private String password;

    /**
     * 部门id
     */
    @TableField("dept_id")
    private String deptId;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 电话
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 状态;(1)true (0)false
     */
    @TableField("status")
    private Short status;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 性别;(0)女(1)男
     */
    @TableField("sex")
    private Short sex;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

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

    @TableField(exist = false)
    private String token;

    @TableField(exist = false)
    private List<Role> sysRole = new ArrayList<>();
    @TableField(exist = false)
    private Set<String> params = new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return params.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }
}
