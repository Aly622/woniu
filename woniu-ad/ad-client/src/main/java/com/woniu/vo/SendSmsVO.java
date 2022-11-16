package com.woniu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/************************************** 
 * @program: sh-diap
 * @author: mike.ma
 * @desc: 发送短信入参  
 * @date: 2022-04-25 11:02  
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendSmsVO {

    /**
     * 模板类型（101：验证码，102：报名，103：忘记密码，201：发奖，202：补发）
     */
    private Integer templateType;

    /**
     * 手机号
     */
    private String mobile;

}
