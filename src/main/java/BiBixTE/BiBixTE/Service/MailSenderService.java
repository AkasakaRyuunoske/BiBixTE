package BiBixTE.BiBixTE.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String userMail;

    public void send(String emailTo, String subject, String message) throws MessagingException {
        MimeMessage The_Dying_Message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(The_Dying_Message,true);

        helper.setFrom(userMail);
        helper.setTo(emailTo);
        helper.setSubject(subject);
        helper.setText(message);

        mailSender.send(The_Dying_Message);

        log.info("==========================");
        log.info("User mail: " + userMail);
        log.info("Email to is: " + emailTo);
        log.info("Subject is: " + subject);
        log.info("Message is: " + message);
        log.info("==========================");
    }
}
