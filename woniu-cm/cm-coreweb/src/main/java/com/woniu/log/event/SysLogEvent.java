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

import com.woniu.log.LogBo;
import org.springframework.context.ApplicationEvent;

/*
 * @Author Oliver.Liu
 * @Desc 系统日志事件  spring订阅发布机制
 * @Date 2019/6/10 13:40
 * @Param
 * @return
 **/
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(LogBo source) {
        super(source);
    }
}
