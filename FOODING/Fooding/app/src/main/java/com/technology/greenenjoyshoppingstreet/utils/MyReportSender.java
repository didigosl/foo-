package com.technology.greenenjoyshoppingstreet.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.collections.ImmutableSet;
import org.acra.collector.CrashReportData;
import org.acra.config.ACRAConfiguration;
import org.acra.model.Element;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by Administrator on 2017/6/3.
 */

public class MyReportSender implements ReportSender {
    private Context contxet;
    private ACRAConfiguration config;

    public MyReportSender(Context contxet, ACRAConfiguration config) {
        this.contxet = contxet;
        this.config = config;
    }

    @Override
    public void send(Context context, CrashReportData report) throws ReportSenderException {
        final String body = buildBody(report);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.126.com");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yangnaiting@126.com", "niu123456");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yangnaiting@126.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    "yangnaiting@126.com"));
            message.setSubject("24购崩溃报告");
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    private String buildBody(@NonNull CrashReportData errorContent) {
        Set<ReportField> fields = config.getReportFields();
        if (fields.isEmpty()) {
            fields = new ImmutableSet<ReportField>(ACRAConstants.DEFAULT_REPORT_FIELDS);
        }

        final StringBuilder builder = new StringBuilder();
        for (ReportField field : fields) {
            builder.append(field.toString()).append('=');
            Element value = errorContent.get(field);
            if (value != null) {
                builder.append(TextUtils.join("\n\t", value.flatten()));
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
