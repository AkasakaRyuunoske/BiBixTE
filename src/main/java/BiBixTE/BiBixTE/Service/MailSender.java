package BiBixTE.BiBixTE.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String userMail;

    public void send(String emailTo, String subject, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(userMail);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        log.info("===============");
        log.info("User mail: " + userMail);
        log.info("Email to is: " + emailTo);
        log.info("Subject is: " + subject);
        log.info("Message is: " + message);
    }
}
