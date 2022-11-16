package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public class UcUserRoleRelation extends Model<UcUserRoleRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID（uc_user表的主键ID）
     */
    private Long userId;

    /**
     * 角色ID（uc_role表的主键ID）
     */
    private Long roleId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
        return "UcUserRoleRelation{" +
        "userId=" + userId +
        ", roleId=" + roleId +
        ", createdBy=" + createdBy +
        ", createdByName=" + createdByName +
        ", createdAt=" + createdAt +
        ", tenantId=" + tenantId +
        "}";
    }
}
