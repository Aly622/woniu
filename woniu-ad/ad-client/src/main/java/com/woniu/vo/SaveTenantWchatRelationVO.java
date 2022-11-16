package com.woniu.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveTenantWchatRelationVO {

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




}
