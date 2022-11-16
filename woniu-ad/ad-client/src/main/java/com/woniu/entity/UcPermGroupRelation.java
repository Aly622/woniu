package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.esmartwave.niumeng.diap.entity.SuperEntity;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜单组权限关系表
 * </p>
 *
 * @author Admin
 * @since 2022-03-10
 */
@Data
public class UcPermGroupRelation extends Model<UcPermGroupRelation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限组id")
    private Long groupId;

    @ApiModelProperty(value = "权限id")
    private Long permId;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人id")
    private Long createdBy;

    /**
     * 创建人姓名
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人名称")
    private String createdByName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;



}
