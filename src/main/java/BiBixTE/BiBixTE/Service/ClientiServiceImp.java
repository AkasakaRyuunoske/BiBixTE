package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Entity.VerificationToken;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Repository.VerificationTokenRepository;
import BiBixTE.BiBixTE.model.ClientiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Slf4j
@Service
public class ClientiServiceImp implements ClientiService{
    @Autowired
    private ClientiRepository clientiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private MailSender mailSender;


    @Override
    public Clienti registerClienti(Clienti clienti) {


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
        return clienti;
    }

    @Override
    public void saveVerificationTokneForClienti(String token, Clienti clienti) {
        VerificationToken verificationToken = new VerificationToken(clienti, token);

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public boolean activateUser(String code) {
        Clienti clienti = clientiRepository.findByActivationCode(code);

        if (clienti == null){
            System.out.println("Errore di registrazione.");;
        }

        assert clienti != null;
        clienti.setActivationCode("null");
        clientiRepository.save(clienti);

        return true;
    }


}
