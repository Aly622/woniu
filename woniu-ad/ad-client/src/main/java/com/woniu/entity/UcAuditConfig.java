package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 审批流配置表
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public class UcAuditConfig extends Model<UcAuditConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "审批流ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 审批流名称
     */
    @ApiModelProperty(value = "审批流名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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
     * 租户编号
     */
    @TableField(fill = FieldFill.INSERT)
    private Long tenantId;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UcAuditConfig{" +
        "id=" + id +
        ", name=" + name +
        ", remark=" + remark +
        ", createdBy=" + createdBy +
        ", createdByName=" + createdByName +
        ", createdAt=" + createdAt +
        ", updatedBy=" + updatedBy +
        ", updatedByName=" + updatedByName +
        ", updatedAt=" + updatedAt +
        ", tenantId=" + tenantId +
        "}";
    }
}
