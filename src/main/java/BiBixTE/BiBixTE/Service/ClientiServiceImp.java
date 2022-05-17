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
                    + "Sanman de fuantei na kono sekai \n"
                    + "Hakumei ho okoshite yo \n"
                    + "'AI' ga seikai ni nareeee \n"
                    + "fuante nantei kankan ni kiechate \n"
                    + "Hakumei no hi terasu sono mukou heee \n"
                    + "Kiroku mo kioku mo nai hito maishou \n"
                    + "Kibuo mo mirai mo nai kuni gaishou \n"
                    + "Nagai yoru wo oe asayake wo mita \n"
                    + "akaku somaru senaka tooooooooo \n"
                    + "NEE ANATA WA ITSUKA INAKU NARI MASU KA? \n"
                    + "OWARI KAKE NO KONO SEKAI DE \n"
                    + "YAOKE MAE NO UREI TAEGATAI KODOKU \n"
                    + "AWAI OMOI TO SEKIBAKU WO DAITAAAAA \n"
                    + "Actiovation here: http://localhost:8080/activate/%s",
                    clienti.getUserName(),
                    clienti.getActivationCode());

            mailSender.send(clienti.getEmail(), "Activation of account", theDyingMessage);
        }
    }

    @Override
    public boolean activateUser(String userName) {
        Clienti clienti = clientiRepository.findByUserName(userName);

        assert clienti != null;
        clienti.setActivationCode("ACTIVATED");
        clientiRepository.save(clienti);
        log.info("CLient activated! " + userName);
        return true;
    }


}
