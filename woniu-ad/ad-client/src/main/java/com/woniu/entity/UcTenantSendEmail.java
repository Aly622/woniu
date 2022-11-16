package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.esmartwave.niumeng.diap.entity.SuperEntity;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 租户表发送邮件邮箱配置表
 * </p>
 *
 * @author Admin
 * @since 2022-04-19
 */
public class UcTenantSendEmail extends Model<UcTenantSendEmail> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮箱类型 1:预设邮箱; 2:自定义")
    private Integer emailType;

    @ApiModelProperty(value = "预设邮箱  1:源慧科技; 2:硕苗科技; email_type=1有值")
    private Integer presetEmail;

    @ApiModelProperty(value = "自定义邮箱服务器地址 ")
    private String serverAddress;

    @ApiModelProperty(value = "自定义发件人邮箱账号")
    private String emailAccount;

    @ApiModelProperty(value = "自定义发件人邮箱密码")
    private String emailPassword;

    @ApiModelProperty(value = "自定义发件人邮箱端口")
    private String port;

    @ApiModelProperty(value = "自定义是否为ssl安全端口1:是; 0:不是")
    private Integer isSsl;

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

    public Integer getEmailType() {
        return emailType;
    }

    public void setEmailType(Integer emailType) {
        this.emailType = emailType;
    }

    public Integer getPresetEmail() {
        return presetEmail;
    }

    public void setPresetEmail(Integer presetEmail) {
        this.presetEmail = presetEmail;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getIsSsl() {
        return isSsl;
    }

    public void setIsSsl(Integer isSsl) {
        this.isSsl = isSsl;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UcTenantSendEmail{" +
        "emailType=" + emailType +
        ", presetEmail=" + presetEmail +
        ", serverAddress=" + serverAddress +
        ", emailAccount=" + emailAccount +
        ", emailPassword=" + emailPassword +
        ", port=" + port +
        ", isSsl=" + isSsl +
        "}";
    }
}
