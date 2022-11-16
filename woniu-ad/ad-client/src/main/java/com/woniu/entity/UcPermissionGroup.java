package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限组表
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public class UcPermissionGroup extends Model<UcPermissionGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜单组名称
     */
    private String name;

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

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UcPermissionGroup{" +
        "id=" + id +
        ", name=" + name +
        ", createdBy=" + createdBy +
        ", createdByName=" + createdByName +
        ", createdAt=" + createdAt +
        "}";
    }
}
