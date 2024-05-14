package com.oik.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.oik.api.config.note.IdRule;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 自定义id生产规则
 */
@Getter
@Setter
@TableName("sys_number_rules")
@Accessors(chain = true)
@IdRule(code = "RULE")
public class NumberRules implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * code
     */
    @TableField("code")
    private String code;

    /**
     * 日期规则
     */
    @TableField("regular")
    private String regular;

    /**
     * 连接符
     */
    @TableField("connectors")
    private String connectors;

    /**
     * 数字
     */
    @TableField("num")
    private Integer num;

    @TableField("date_str")
    private String dateStr;
}
