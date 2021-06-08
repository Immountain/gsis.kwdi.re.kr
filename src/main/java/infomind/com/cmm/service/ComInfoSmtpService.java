package infomind.com.cmm.service;


import infomind.com.cms.info.mail.dao.InfoMailSendDAO;
import infomind.com.cms.info.mail.vo.InfoMailSendVO;
import infomind.com.utils.StreamUtils;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.AsyncResponse;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.Message;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ComInfoSmtpService implements InitializingBean {

    private final Map<String, Mailer> mailers = new HashMap<>();

    @Resource(name = "InfoMailSendDAO")
    private InfoMailSendDAO infoMailSendDAO;

    @Override
    public void afterPropertiesSet() throws Exception {

        // TODO 속성정보 불러와서 처리할 것 (사이트 : N)
        Mailer mailer = MailerBuilder.withDebugLogging(true)
                .withSMTPServerHost("smtp.sendgrid.net")
                .withSMTPServerPort(587)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSMTPServerUsername("apikey")
                .withSMTPServerPassword("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")
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withThreadPoolKeepAliveTime(5000)
                .async()
                .buildMailer();

        mailers.put("default", mailer);

        // Test Mail Send
        // Email email = EmailBuilder.startingBlank()
        //         .from("조동국", "jodongguk@gmail.com")
        //         .to("조동국", "jodongguk@gmail.com")
        //         .withSubject("hey")
        //         .withHTMLText("<img src='cid:wink1'><b>We should meet up!</b><img src='cid:wink2'>")
        //         .withPlainText("Please view this email in a modern email client!")
        //         .buildEmail();
        // mailer.sendMail(email);
    }

    public void sendMail(String key, Email email) {
        AsyncResponse asyncResponse = mailers.get(key).sendMail(email, true);
        asyncResponse.onSuccess(() -> {
            infoMailSendDAO.insert(InfoMailSendVO.builder()
                    .mailTo(email.getRecipients().stream()
                            .filter(recipient -> recipient.getType() == Message.RecipientType.TO)
                            .map( n -> n.getAddress() )
                            .collect( Collectors.joining( ", " )))
                    .mailCc(email.getRecipients().stream()
                            .filter(recipient -> recipient.getType() == Message.RecipientType.CC)
                            .map( n -> n.getAddress() )
                            .collect( Collectors.joining( ", " )))
                    .mailFrom(email.getFromRecipient().getAddress())
                    .mailSubject(email.getSubject())
                    .mailContent(email.getHTMLText())
                    .mailSendType("success")
                    .build());
        });
        asyncResponse.onException(e -> {
            infoMailSendDAO.insert(InfoMailSendVO.builder()
                    .mailTo(email.getRecipients().stream()
                            .filter(recipient -> recipient.getType() == Message.RecipientType.TO)
                            .map( n -> n.getAddress() )
                            .collect( Collectors.joining( ", " )))
                    .mailCc(email.getRecipients().stream()
                            .filter(recipient -> recipient.getType() == Message.RecipientType.CC)
                            .map( n -> n.getAddress() )
                            .collect( Collectors.joining( ", " )))
                    .mailFrom(email.getFromRecipient().getAddress())
                    .mailSubject(email.getSubject())
                    .mailContent(email.getHTMLText())
                    .mailSendType("error")
                    .mailErrorMsg(e.getMessage())
                    .build());
        });
    }
}
