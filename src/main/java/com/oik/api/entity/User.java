package com.oik.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
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
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private String id;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField("`PASSWORD`")
    private String password;

    /**
     * 部门id
     */
    @TableField("DEPT_ID")
    private String deptId;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 电话
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 状态;(1)true (0)false
     */
    @TableField("`STATUS`")
    private Byte status = 0;

    /**
     * 最后登录时间
     */
    @TableField("LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    /**
     * 性别;(0)女(1)男
     */
    @TableField("SEX")
    private Byte sex;

    /**
     * 描述
     */
    @TableField("`DESCRIPTION`")
    private String description;

    /**
     * 头像
     */
    @TableField("AVATAR")
    private String avatar;

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
