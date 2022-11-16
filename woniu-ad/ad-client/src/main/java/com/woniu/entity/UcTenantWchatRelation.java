package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.esmartwave.niumeng.diap.entity.SuperEntity;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 租户微信权表
 * </p>
 *
 * @author Admin
 * @since 2022-04-11
 */
public class UcTenantWchatRelation extends Model<UcTenantWchatRelation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小程序appid")
    private String appId;

    @ApiModelProperty(value = "微信名称")
    private String wechatName;

    @ApiModelProperty(value = "类型标识")
    private Integer wechatTypeDefine;

    @ApiModelProperty(value = "微信类型名称")
    private String wechatType;

    @ApiModelProperty(value = "主体信息")
    private String openMainBody;

    @ApiModelProperty(value = "微信头像")
    private String headImgUrl;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "状态名称")
    private String stateName;

    @ApiModelProperty(value = "状态 1:启用; 其他:冻结")
    private Integer state;

    @ApiModelProperty(value = "开发者id")
    private String componentAppId;


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




    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public Integer getWechatTypeDefine() {
        return wechatTypeDefine;
    }

    public void setWechatTypeDefine(Integer wechatTypeDefine) {
        this.wechatTypeDefine = wechatTypeDefine;
    }

    public String getWechatType() {
        return wechatType;
    }

    public void setWechatType(String wechatType) {
        this.wechatType = wechatType;
    }

    public String getOpenMainBody() {
        return openMainBody;
    }

    public void setOpenMainBody(String openMainBody) {
        this.openMainBody = openMainBody;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getComponentAppId() {
        return componentAppId;
    }

    public void setComponentAppId(String componentAppId) {
        this.componentAppId = componentAppId;
    }

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

    @Override
    protected Serializable pkVal() {
        return null;
    }


    @Override
    public String toString() {
        return "UcTenantWchatRelation{" +
                "appId='" + appId + '\'' +
                ", wechatName='" + wechatName + '\'' +
                ", wechatTypeDefine=" + wechatTypeDefine +
                ", wechatType='" + wechatType + '\'' +
                ", openMainBody='" + openMainBody + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", supplierId=" + supplierId +
                ", stateName='" + stateName + '\'' +
                ", state=" + state +
                ", componentAppId='" + componentAppId + '\'' +
                ", id=" + id +
                ", createdBy=" + createdBy +
                ", createdByName='" + createdByName + '\'' +
                ", createdAt=" + createdAt +
                ", tenantId=" + tenantId +
                '}';
    }
}
