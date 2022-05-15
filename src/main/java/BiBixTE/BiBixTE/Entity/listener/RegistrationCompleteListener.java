package BiBixTE.BiBixTE.Entity.listener;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Service.ClientiService;
import BiBixTE.BiBixTE.event.RegistrationComplete;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompleteListener implements ApplicationListener<RegistrationComplete> {

    @Autowired
    private ClientiService clientiService;

    @Override
    public void onApplicationEvent(RegistrationComplete event) {
        Clienti clienti = event.getClienti();
        String token = UUID.randomUUID().toString();

        clientiService.saveVerificationTokneForClienti(token, clienti);

        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;

        //todo verification method and email sending method
        log.info("Click the link {}", url);
    }
}
