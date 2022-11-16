package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 审批流配置详情表
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public class UcAuditConfigDetail extends Model<UcAuditConfigDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 审批流配置主键ID
     */
    private Long configId;

    /**
     * 审批流节点ID（角色ID）
     */
    private Long roleId;

    /**
     * 审批流节点排序
     */
    private Integer flowOrder;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createdBy;

    /**
     * 创建人姓名
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdByName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 租户编号
     */
    @TableField(fill = FieldFill.INSERT)
    private Long tenantId;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getFlowOrder() {
        return flowOrder;
    }

    public void setFlowOrder(Integer flowOrder) {
        this.flowOrder = flowOrder;
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

    public Long getTenantId() { return tenantId; }

    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UcAuditConfigDetail{" +
        "configId=" + configId +
        ", roleId=" + roleId +
        ", flowOrder=" + flowOrder +
        ", createdBy=" + createdBy +
        ", createdByName=" + createdByName +
        ", createdAt=" + createdAt +
        ", tenantId=" + tenantId +
        "}";
    }
}
