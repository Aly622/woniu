package com.woniu.filter;

import com.alibaba.fastjson.JSON;
import com.woniu.JwtAuthConfig;
import com.woniu.TokenUser;
import com.woniu.contants.CommonConstants;
import com.woniu.enums.SourceEntry;
import com.woniu.response.GwResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @创建人 Oliver.Liu
 * @创建时间 02/21/2022
 * @描述 过滤器 token
 */
@Slf4j
@Component
public class TokenFilter extends BaseFilter implements GlobalFilter, Ordered {
    @Autowired
    private JwtAuthConfig jwtConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethodValue();
        String url = request.getURI().getPath();

        //从 header 中读取
        Integer entry = null;
        if(request.getHeaders().containsKey(CommonConstants.ENTRY)) {
            entry = Integer.valueOf(request.getHeaders().getFirst(CommonConstants.ENTRY));
        }
        Long tenantId = null;
        if(request.getHeaders().containsKey(CommonConstants.TENANTID)) {
            tenantId = Long.valueOf(request.getHeaders().getFirst(CommonConstants.TENANTID));
        }
        Long campaignId = null;
        if(request.getHeaders().containsKey(CommonConstants.CAMPAIGNID)) {
            campaignId = Long.valueOf(request.getHeaders().getFirst(CommonConstants.CAMPAIGNID));
        }
        String headerToken = request.getHeaders().getFirst(CommonConstants.OAUTH_TOKEN);
        String apiToken = request.getHeaders().getFirst(CommonConstants.API_TOKEN);

        log.info("----TokenFilter {}, {}, {}, {}, {}, {}, {}, {}", method, url, headerToken, tenantId, campaignId, apiToken, entry);
        if(entry == null) {
            if(!WhiteListUtils.isEntryWhiteList(url)) {
                log.info("非法访问,确实调用方标识！");
                return response(exchange.getResponse(), GwResponseCode.ILLEGAL_ENTRY);
            }
        }
        else if (SourceEntry.B_TENANT_END.ordinal() == entry
            || SourceEntry.B_PLATFORM_END.ordinal() == entry)
        {
            // B端接口
            if(headerToken != null && headerToken.startsWith(jwtConfig.getPrefix() + " ")) {
                if(tenantId == null || tenantId <= 0) {
                    return response(exchange.getResponse(), GwResponseCode.TENANTID_CAN_NOT_BE_EMPTY);
                }
                headerToken = headerToken.replace(jwtConfig.getPrefix() + " ", "");
                try {
                    Claims claims = Jwts.parser()
                            .setSigningKey(jwtConfig.getSecret().getBytes())
                            .parseClaimsJws(headerToken)
                            .getBody();
                    Date expiration = claims.getExpiration();
                    if(expiration.before(new Date())) {
                        log.warn("token is expired");
                        return response(exchange.getResponse(), GwResponseCode.TOKEN_IS_EXPIRED);
                    }
                    String userInfo = claims.get("userInfo", String.class);
                    TokenUser user = JSON.parseObject(userInfo, TokenUser.class);
                    user.setTenantId(tenantId);
                    user.setEntry(entry);
                    request.mutate().headers(httpHeaders -> {
                        httpHeaders.set("Content-Type", "application/json;charset=UTF-8");
                        httpHeaders.set(CommonConstants.USER_ID, JSON.toJSONString(user));
                    });
                    log.info("access token ok");
                } catch (Exception e) {
                    log.warn("invalid access token");
                    return response(exchange.getResponse(), GwResponseCode.ILLEGAL_TOKEN);
                }
            } else if(WhiteListUtils.isInTokenUserList(url)) {
                log.info("进入tokenuser 例外名单");
            } else if(!WhiteListUtils.isInWhiteList(url)) {
                log.info("进入非法访问");
                log.warn("access token is empty");
                return response(exchange.getResponse(), GwResponseCode.TOKEN_CAN_NOT_BE_EMPTY);
            } else {
                log.info("进入白名单");
            }
        }
        else if(SourceEntry.C_END.ordinal() == entry) //C端接口
        {
            if(WhiteListUtils.isInAPIHeadTokenList(url)) {
                log.info("进入api head token 例外名单");
                if(tenantId == null
                        && StringUtils.isEmpty(apiToken)
                        && campaignId == null) {
                    log.warn("api head token is empty");
                    response(exchange.getResponse(), GwResponseCode.TOKEN_CAN_NOT_BE_EMPTY);
                }
                String APITOKEN = DigestUtils.md5Hex(DigestUtils.md5Hex("YH"+campaignId));

                TokenUser user = new TokenUser();
                user.setEntry(entry);
                user.setTenantId(tenantId);
                user.setApiToken(apiToken);
                user.setCampaignId(campaignId);
                request.mutate().headers(httpHeaders -> {
                    httpHeaders.set(CommonConstants.USER_ID, JSON.toJSONString(user));
                });
            } else {

            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return JWT_ORDER;
    }

    private String getClientIp(ServerHttpRequest request) {
        String ip = request.getHeaders().getFirst("X-Forwarded-For");
        log.debug("X-Forwarded-For : {}", ip);
        if (org.springframework.util.StringUtils.hasText(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeaders().getFirst("X-Real-IP");
        if (org.springframework.util.StringUtils.hasText(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        String hostAddress = "Unknown hostAddress";
        if (request.getRemoteAddress() != null) {
            hostAddress = request.getRemoteAddress().getAddress().getHostAddress();
        }
        return hostAddress;
    }
}
