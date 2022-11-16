/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.woniu.utils;

import com.alibaba.fastjson.JSONObject;
import com.esmartwave.niumeng.diap.contants.CommonConstants;
import com.esmartwave.niumeng.diap.extend.TokenUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
 * @Author Oliver.Liu
 * @Desc //安全工具类
 * @Date 2019/6/17 15:33
 * @Param
 * @return
 **/
@Slf4j
public class SecurityUtils {

    public SecurityUtils() {
    }

    public static void setUser(Long tenantId) {
        TokenUser user = TokenUser.getInstance();
        user.setTenantId(tenantId);
    }

    public static TokenUser getUser() {
        final String FLD_TENANTID = "tenantId";
        final Long DEV_TENANTID = 0001L;
        try {
            String userStr = "";
            HttpServletRequest httpServletRequest = null;
            // 从网络请求发起
            if (RequestContextHolder.getRequestAttributes() != null
                    && ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() != null
                    && !(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() instanceof MockHttpServletRequest)
                    ) {
                httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                userStr = httpServletRequest.getHeader(CommonConstants.USER_ID);
                if (!StringUtils.isEmpty(userStr)) {
                    TokenUser tokenUser = JSONObject.parseObject(userStr, TokenUser.class);
                    return tokenUser;
                } else {
                    Map parameterMap = httpServletRequest.getParameterMap();
                    if (parameterMap.get(FLD_TENANTID) != null) {
                        TokenUser tokenUser = new TokenUser();
                        Long[] vals = (Long[]) parameterMap.get("tenantId");
                        tokenUser.setTenantId(vals[0]);
                        return tokenUser;
                    } else {
                        Map pathVariables = (Map) httpServletRequest.getAttribute(
                                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                        if (pathVariables != null && pathVariables.get(FLD_TENANTID) != null) {
                            TokenUser tokenUser = new TokenUser();
                            tokenUser.setTenantId((Long) pathVariables.get(FLD_TENANTID));
                            return tokenUser;
                        }
                    }
                }
            }else {//从测试类或其他发起
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                List<StackTraceElement> list = Arrays.asList(stackTrace);
                if(list.stream().anyMatch((e) -> e.getClassName().startsWith("org.junit."))) { //  测试环境
                    TokenUser tokenUser = new TokenUser();
                    tokenUser.setTenantId(DEV_TENANTID);
                    return tokenUser;
                }
            }
            return TokenUser.getInstance();
        } catch (Exception e) {
            log.error("token user 获取发生异常", e);
            return TokenUser.getInstance();
        }
    }

    public static void reset() {
        TokenUser.localTokenUser.remove();
    }
}
