package com.woniu.dto;

import lombok.Data;

/************************************** 
 * @program: sh-diap
 * @author: mike.ma
 * @desc: nickName和userName对象  
 * @date: 2022-04-22 17:30  
 **************************************/
@Data
public class UserNameAndNickNameDTO {

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;
}
