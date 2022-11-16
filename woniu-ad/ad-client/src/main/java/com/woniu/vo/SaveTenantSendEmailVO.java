package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class SaveTenantSendEmailVO {

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

}
