package BiBixTE.BiBixTE.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String hostMail;

    @Value("${spring.mail.username}")
    private String userMail;

    @Value("${spring.mail.password}")
    private String passwordMail;

    @Value("${spring.mail.port}")
    private int portMail;

    @Value("${spring.mail.protocol}")
    private String protocolMail;

    @Value("${mail.debug}")
    private String debugMail;

    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(hostMail);
        javaMailSender.setPort(portMail);
        javaMailSender.setUsername(userMail);
        javaMailSender.setPassword(passwordMail);

        Properties properties = javaMailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", protocolMail);

        //must be commented when pushing to prod
        properties.setProperty("mail.debug", debugMail);

        return javaMailSender;
    }
}
