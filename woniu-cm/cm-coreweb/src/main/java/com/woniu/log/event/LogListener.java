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

package com.woniu.log.event;

import com.woniu.SysLog;
import com.woniu.service.impl.LogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/*
 * @Author Oliver.Liu
 * @Desc //异步监听日志事件
 * @Date 2019/6/10 18:00
 * @Param
 * @return
 **/
@Slf4j
public class LogListener {
    @Autowired
    private LogServiceImpl logService;
    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        logService.saveLog(sysLog);
    }
}
