package BiBixTE.BiBixTE;

import BiBixTE.BiBixTE.Service.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BiBixTeApplication {
	public static void main(String[] args) {
		SpringApplication.run(BiBixTeApplication.class, args);
	}
}