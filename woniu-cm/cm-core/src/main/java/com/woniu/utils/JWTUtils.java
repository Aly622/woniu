package com.woniu.utils;

import com.alibaba.fastjson.JSON;
import com.woniu.config.JwtAuthConfig;
import com.woniu.extend.TokenUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName JWTUtils.java
 * @Description TODO
 * @createTime 2022年02月24日 13:58:00
 */

public class JWTUtils {
    /**
     * 从数据声明生成令牌
     * @param tokenUser 登录用户
     * @return 令牌
     */
    public String generateToken(TokenUser tokenUser, JwtAuthConfig jwtConfig) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userInfo", JSON.toJSONString(tokenUser));
        Instant now = Instant.now();
        String token = Jwts.builder()
                .setClaims(claims)// 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setSubject(tokenUser.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(jwtConfig.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret().getBytes())
                .compact();
        return token;
    }
    /**
     * 从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token, byte[] secret) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 从令牌中获取用户名
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token, byte[] secret) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token, secret);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token, byte[] secret) {
        try {
            Claims claims = getClaimsFromToken(token, secret);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
