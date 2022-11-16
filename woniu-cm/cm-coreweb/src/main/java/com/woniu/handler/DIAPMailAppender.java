package com.woniu.handler;

import com.esmartwave.niumeng.diap.config.DynamicsValConfig;
import com.esmartwave.niumeng.diap.contants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;

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
        this.evaluator = new DIAPEvaluator();
    }

    private void updateSubject(String subject) {
        String applicationName = DynamicsValConfig.subjectSource;
        Boolean replaceAll = DynamicsValConfig.replaceAll;
        String finalSubject = subject;
        try {
            if (subject != null) {
                try {
                    // 增加参数
                    if (!replaceAll) {
                        if (StringUtils.isNotEmpty(applicationName)) {
                            finalSubject = finalSubject.replace("{applicationName}", applicationName);
                        }
                    }
                    msg.setSubject(MimeUtility.encodeText(finalSubject, "UTF-8", null));
                } catch (UnsupportedEncodingException ex) {
                    log.error("Unable to encode SMTP subject", ex);
                }
            }
        } catch (MessagingException e) {
            log.error("Could not activate SMTPAppender options.", e);
        }
    }

    @Override
    public void append(LoggingEvent event) {
        if (!checkEntryConditions()) {
            return;
        }
        event.getThreadName();
        event.getNDC();
        event.getMDCCopy();
        if (this.getLocationInfo()) {
            event.getLocationInformation();
        }
        event.getRenderedMessage();
        event.getThrowableStrRep();
        cb.add(event);
        if (evaluator.isTriggeringEvent(event)) {
            sendBuffer();
        }
    }

    @Override
    protected void sendBuffer() {
        try {
            this.updateSubject(super.getSubject());
            String s = formatBody();
            boolean allAscii = true;
            for (int i = 0; i < s.length() && allAscii; i++) {
                allAscii = s.charAt(i) <= 0x7F;
            }
            MimeBodyPart part;
            if (allAscii) {
                part = new MimeBodyPart();
                part.setContent(s, layout.getContentType());
            } else {
                try {
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    Writer writer = new OutputStreamWriter(
                            MimeUtility.encode(os, "quoted-printable"), "UTF-8");
                    writer.write(s);
                    writer.close();
                    InternetHeaders headers = new InternetHeaders();
                    headers.setHeader("Content-Type", layout.getContentType() + "; charset=UTF-8");
                    headers.setHeader("Content-Transfer-Encoding", "quoted-printable");
                    part = new MimeBodyPart(headers, os.toByteArray());
                } catch (Exception ex) {
                    StringBuffer sbuf = new StringBuffer(s);
                    for (int i = 0; i < sbuf.length(); i++) {
                        if (sbuf.charAt(i) >= 0x80) {
                            sbuf.setCharAt(i, '?');
                        }
                    }
                    part = new MimeBodyPart();
                    part.setContent(sbuf.toString(), layout.getContentType());
                }
            }
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(part);
            msg.setContent(mp);

            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (MessagingException e) {
            log.error("Error occured while sending e-mail notification.", e);
        } catch (RuntimeException e) {
            log.error("Error occured while sending e-mail notification.", e);
        }
    }

    class DIAPEvaluator implements TriggeringEventEvaluator {
        @Override
        public boolean isTriggeringEvent(LoggingEvent event) {
            return event.getLevel().isGreaterOrEqual(Level.ERROR)
                    && CommonConstants.MAIL_NOTIFY.equals(DynamicsValConfig.enableMailNotify)
                    && StringUtils.isNotEmpty(event.getNDC()) && CommonConstants.MAIL_NOTIFY.equals(event.getNDC());
        }
    }
}