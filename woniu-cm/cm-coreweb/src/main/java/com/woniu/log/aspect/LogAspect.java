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

package com.woniu.log.aspect;

import com.alibaba.fastjson.JSON;
import com.esmartwave.niumeng.diap.contants.ResponseCodeConstans;
import com.woniu.SysLog;
import com.esmartwave.niumeng.diap.exception.ServiceException;
import com.woniu.log.annotation.Log;
import com.woniu.log.event.LogEvent;
import com.esmartwave.niumeng.diap.utils.SpringAppContextUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/*
 * @Author Oliver.Liu
 * @Desc //操作日志异步入库  环绕通知
 * @Param
 * @return
 **/
@Aspect
@Slf4j
public class LogAspect {
    //使用切点增强的时机注解:@Before,@Around,@AfterReturning,@AfterThrowing,@After
    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, Log sysLog) {
        Object obj = null;
        try {
            String strClassName = point.getTarget().getClass().getName();
            String strMethodName = point.getSignature().getName();
            log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);
            HttpServletRequest request = ((ServletRequestAttributes) Objects
                    .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            SysLog logVo = new SysLog();
            logVo.setServiceType(request.getRequestURI());
            logVo.setServiceId(String.format("%s-%s", SpringAppContextUtils.getApplicationContext().getApplicationName(), sysLog.value()));
            // 发送异步日志事件
            Long startTime = System.currentTimeMillis();
            if(!request.getRequestURI().contains("download")) {
                logVo.setRequestParam(JSON.toJSONString(point.getArgs()));
            }
            logVo.setRequestDateTime(LocalDateTime.now());
            obj = point.proceed();
            Long endTime = System.currentTimeMillis();
            log.debug("[对象信息]:{}", JSON.toJSONString(logVo));
            logVo.setResponseResult(String.format("[执行时间]：%s %s", endTime - startTime, obj.toString()));
            SpringAppContextUtils.publishEvent(new LogEvent(logVo));
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new ServiceException(ResponseCodeConstans.SYSTEM_EXCEPTION_CODE, e);
        }
        return obj;
    }
}
