package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 组织架构子表
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public class UcOrgDetail extends Model<UcOrgDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 组织ID
     */
    private Long orgId;

    /**
     * 子组织ID
     */
    private Long childOrgId;

    /**
     * 是否是当前部门（0：不是，1：是）
     */
    private Integer isCurrent;

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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getChildOrgId() {
        return childOrgId;
    }

    public void setChildOrgId(Long childOrgId) {
        this.childOrgId = childOrgId;
    }

    public Integer getIsCurrent() { return isCurrent; }

    public void setIsCurrent(Integer isCurrent) { this.isCurrent = isCurrent; }

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
        return "UcOrgDetail{" +
        "orgId=" + orgId +
        ", childOrgId=" + childOrgId +
        ", isCurrent=" + isCurrent +
        ", createdBy=" + createdBy +
        ", createdByName=" + createdByName +
        ", createdAt=" + createdAt +
        ", tenantId=" + tenantId +
        "}";
    }
}
