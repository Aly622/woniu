package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public class UcTenant extends Model<UcTenant> {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "Id")
    private Long id;

    /**
     * 租户名称
     */
    @ApiModelProperty(value = "租户名称")
    private String name;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    private String code;

    /**
     * 状态 1:启用; 0:禁用
     */
    @ApiModelProperty(value = "状态 1:启用; 0:禁用")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String description;

    /**
     * 是否可编辑（0：可编辑，1：不可编辑）
     */
    @ApiModelProperty(value = "是否可编辑（0：可编辑，1：不可编辑）")
    private Integer allowEdit;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createdBy;

    /**
     * 创建人姓名
     */
    @ApiModelProperty(value = "创建人姓名")
    @TableField(fill = FieldFill.INSERT)
    private String createdByName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updatedBy;

    /**
     * 修改人姓名
     */
    @ApiModelProperty(value = "修改人姓名")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedByName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 是否删除 0-正常  1-已删除
     */
    @ApiModelProperty(value = "是否删除 0-正常  1-已删除")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAllowEdit() { return allowEdit; }

    public void setAllowEdit(Integer allowEdit) { this.allowEdit = allowEdit; }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() { return createdByName; }

    public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByName() { return updatedByName; }

    public void setUpdatedByName(String updatedByName) { this.updatedByName = updatedByName; }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "UcTenant{" +
        "name=" + name +
        ", code=" + code +
        ", status=" + status +
        ", allowEdit=" + allowEdit +
        ", createdBy=" + createdBy +
        ", createdByName=" + createdByName +
        ", createdAt=" + createdAt +
        ", updatedBy=" + updatedBy +
        ", updatedByName=" + updatedByName +
        ", updatedAt=" + updatedAt +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
