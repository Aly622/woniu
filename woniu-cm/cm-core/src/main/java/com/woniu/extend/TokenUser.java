package com.woniu.extend;

import lombok.Data;

/**
 * @ClassName TokenUser
 * @Desc TODO
 * @Author Oliver.Liu
 * @Date 2019/8/23 15:31
 * @Version 1.0
 **/
@Data
public class TokenUser {
    public static final ThreadLocal<TokenUser> localTokenUser = new InheritableThreadLocal<>();

    private Integer entry;
    private Long id;
    private String username;
    private String nickName;
    private String password;
    private Long tenantId;

    public static TokenUser getInstance() {
        TokenUser tokenUser = localTokenUser.get();
        if(tokenUser == null) {
            tokenUser = new TokenUser();
            localTokenUser.set(tokenUser);
        }
        return tokenUser;
    }

    public TokenUser reset()
    {
        localTokenUser.remove();
        return this;
    }
}
