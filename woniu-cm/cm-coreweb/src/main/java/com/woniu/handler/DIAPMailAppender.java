package com.woniu.handler;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.boolex.OnErrorEvaluator;
import ch.qos.logback.classic.net.SMTPAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.boolex.EvaluationException;
import com.woniu.DynamicsValConfig;
import com.woniu.contants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Oliver.liu
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2020/10/29 13:42
 */
@Slf4j
public class DIAPMailAppender extends SMTPAppender {
    public DIAPMailAppender() {
        this.eventEvaluator = new DIAPEvaluator();
    }

    @Override
    protected Layout<ILoggingEvent> makeSubjectLayout(String subject) {
        String applicationName = DynamicsValConfig.subjectSource;
        Boolean replaceAll = DynamicsValConfig.replaceAll;
        String finalSubject = subject;
        if (subject != null) {
            // 增加参数
            if (!replaceAll) {
                if (StringUtils.isNotEmpty(applicationName)) {
                    finalSubject = finalSubject.replace("{applicationName}", applicationName);
                }
            }
        }
        return super.makeSubjectLayout(finalSubject);
    }

    class DIAPEvaluator extends OnErrorEvaluator {
        final String mailKey = "sendEmail";
        final Integer mailVal = 1;
        @Override
        public boolean evaluate(ILoggingEvent event) throws NullPointerException, EvaluationException {
            Map<String, String> mdcPropertyMap = event.getMDCPropertyMap();
            return event.getLevel().isGreaterOrEqual(Level.ERROR)
                    && CommonConstants.MAIL_NOTIFY.equals(DynamicsValConfig.enableMailNotify)
                    && CommonConstants.MAIL_NOTIFY.equals(mdcPropertyMap.get(mailKey));
        }
    }
}



