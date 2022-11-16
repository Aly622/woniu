package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public class UcUser extends Model<UcUser> {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String headImgUrl;

    /**
     * 账号类型（1：平台，2：租户，3：生产商）
     */
    private Integer type;

    /**
     * 状态（0：停用，1：启用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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
     * 是否删除 0-正常  1-已删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UcUser{" +
        "id=" + id +
        ", nickName=" + nickName +
        ", userName=" + userName +
        ", password=" + password +
        ", email=" + email +
        ", mobile=" + mobile +
        ", headImgUrl=" + headImgUrl +
        ", type=" + type +
        ", status=" + status +
        ", remark=" + remark +
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
