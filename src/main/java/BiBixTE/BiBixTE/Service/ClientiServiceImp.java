package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Slf4j
@Service
public class ClientiServiceImp implements ClientiService{
    @Autowired
    private ClientiRepository clientiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;


    @Override
    public void registerClienti(Clienti clienti) throws MessagingException {
        if (!clienti.getEmail().isEmpty()){
            log.info("=====================================");
            log.info("Clienti service imp was called");
            log.info("Email is: " + clienti.getEmail());
            log.info("User name is: " + clienti.getUserName());
            log.info("Code is: " + clienti.getActivationCode());
            log.info("=====================================");

            String theDyingMessage = String.format("Hello, %s \n"
                    + "Grazie per la sua registrazione sull sito: https://bibixte.herokuapp.com/\n"
                    + "Per attivare il suo account manca l'ultimo step, cioe confermare l'email seguendo link sotto:\n"
                    + "Link per attivare: http://localhost:8080/activate/%s",
                    clienti.getUserName(),
                    clienti.getActivationCode());

            mailSender.send(clienti.getEmail(), "Activation of account", theDyingMessage);
        }
    }

    @Override
    public boolean activateUser(String code) {
        Clienti clienti = clientiRepository.findByActivationCode(code);

        clienti.setActivationCode("ACTIVATED");
        clienti.setConto(50.00);
        clientiRepository.save(clienti);
        log.info("CLient activated! " + clienti.getUserName());
        return true;
    }


}
