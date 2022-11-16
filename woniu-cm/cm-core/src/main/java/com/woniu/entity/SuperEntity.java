package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class SuperEntity<T extends Model<?>> extends Model<T> {

    private static final long serialVersionUID = 1902255219968581538L;
    /**
     * Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;

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

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改人id")
    private Long updatedBy;

    /**
     * 修改人姓名
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改人名称")
    private String updatedByName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField(fill = FieldFill.INSERT)
    private Long tenantId;

    /**
     * 是否删除 0-正常  1-已删除
     */
    @TableLogic
    @ApiModelProperty(value = "是否删除 0-正常  1-已删除")
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
