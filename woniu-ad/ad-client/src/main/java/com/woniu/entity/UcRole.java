package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@AllArgsConstructor
@NoArgsConstructor
public class UcRole extends Model<UcRole> {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "角色ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 数据范围（1：仅本人，2：本部门，3：全部数据）
     */
    @ApiModelProperty(value = "数据范围（1：仅本人，2：本部门，3：全部数据）")
    private Integer scope;

    /**
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否是管理员（0：不是，1：是）
     */
    private Integer isAdmin;

    /**
     * 是否生产商管理员
     */
    private Integer isVendAdmin;

    /**
     * 是否可编辑（0：可编辑，1：不可编辑）
     */
    @ApiModelProperty(value = "是否可编辑（0：可编辑，1：不可编辑）")
    private Integer allowEdit;

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
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updatedBy;

    /**
     * 修改人姓名
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedByName;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 租户编号
     */
    @TableField(fill = FieldFill.INSERT)
    private Long tenantId;

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

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsVendAdmin() {
        return isVendAdmin;
    }

    public void setIsVendAdmin(Integer isVendAdmin) {
        this.isVendAdmin = isVendAdmin;
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

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }


    protected Serializable pkVal() {
        return null;
    }


    @Override
    public String toString() {
        return "UcRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scope=" + scope +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", isAdmin=" + isAdmin +
                ", isVendAdmin=" + isVendAdmin +
                ", allowEdit=" + allowEdit +
                ", createdBy=" + createdBy +
                ", createdByName='" + createdByName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                ", updatedByName='" + updatedByName + '\'' +
                ", updatedAt=" + updatedAt +
                ", tenantId=" + tenantId +
                '}';
    }
}
