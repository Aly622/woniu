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
 * 权限表
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public class UcPermission extends Model<UcPermission> {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单code
     */
    private Integer code;

    /**
     * 菜单类型（1：菜单，2：按钮）
     */
    private Integer type;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单跳转URL
     */
    private String url;

    /**
     * 显示顺序
     */
    private Integer displayOrder;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否可见（0：不可见，1：可见）
     */
    private Integer visible;

    /**
     * 是否是平台端菜单（0：管理端，1：租户端）
     */
    private Integer isTenant;

    /**
     * 是否是生产商菜单（0：不允许生产商使用，1：允许生产商使用）
     */
    private Integer allowVend;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getVisible() { return visible; }

    public void setVisible(Integer visible) { this.visible = visible; }

    public Integer getIsTenant() { return isTenant; }

    public void setIsTenant(Integer isTenant) { this.isTenant = isTenant; }

    public Integer getAllowVend() { return allowVend; }

    public void setAllowVend(Integer allowVend) { this.allowVend = allowVend; }

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
        return "UcPermission{" +
        "id=" + id +
        ", name=" + name +
        "name=" + name +
        ", code=" + code +
        ", type=" + type +
        ", parentId=" + parentId +
        ", url=" + url +
        ", displayOrder=" + displayOrder +
        ", icon=" + icon +
        ", visible=" + visible +
        ", isTenant=" + isTenant +
        ", allowVend=" + allowVend +
        ", createdBy=" + createdBy +
        ", createdByName=" + createdByName +
        ", createdAt=" + createdAt +
        "}";
    }
}
