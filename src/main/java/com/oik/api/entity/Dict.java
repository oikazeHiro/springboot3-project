package com.oik.api.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("key")
    private String key;

    @TableField("value")
    private String value;

    @TableField("sort")
    private Integer sort;

    @TableField("parent")
    private String parent;

    @TableField(value = "created_by",fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableField(value = "states")
    private Integer states;

    // Children
    @TableField(exist = false)
    private List<Dict> children = new ArrayList<>();
}
