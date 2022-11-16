package com.woniu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 发送邮件对象  
 * @date: 2022-03-30 10:24  
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailVO {

    /**
     * 发送人邮箱
     */
    private String tagrtEmail;

    /**
     * 模板类型（None：0，验证码：101，报名：102，发奖：201，补发：202）
     */
    private Integer templateType;

    /**
     * 替换字段
     */
    private Map<String, String> data;
}
