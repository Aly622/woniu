package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.esmartwave.niumeng.diap.entity.SuperEntity;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 租户资源授权表
 * </p>
 *
 * @author Admin
 * @since 2022-03-28
 */
public class UcTenantSourceRelation extends Model<UcTenantSourceRelation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源id")
    private Long sourceId;

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
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField(fill = FieldFill.INSERT)
    private Long tenantId;

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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

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

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UcTenantSourceRelation{" +
                "sourceId=" + sourceId +
                ", id=" + id +
                ", createdBy=" + createdBy +
                ", createdByName='" + createdByName + '\'' +
                ", createdAt=" + createdAt +
                ", tenantId=" + tenantId +
                '}';
    }
}
