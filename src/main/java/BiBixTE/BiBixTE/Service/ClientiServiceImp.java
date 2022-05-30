package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Acquisti;
import BiBixTE.BiBixTE.Entity.Bibite;
import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Entity.Corrieri;
import BiBixTE.BiBixTE.Repository.AcquistiRepository;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Repository.CorrieriRepository;
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
    private MailSenderService mailSenderService;

    @Autowired
    private CorrieriRepository corrieriRepository;

    @Autowired
    private AcquistiRepository acquistiRepository;

    @Override
    public void sendConfirmMail(Clienti clienti) throws MessagingException {
        if (!clienti.getEmail().isEmpty()){
            log.info("=====================================");
            log.info("Clienti service imp was called");
            log.info("Email is: " + clienti.getEmail());
            log.info("User name is: " + clienti.getUserName());
            log.info("Code is: " + clienti.getCodiceDiAttivazione());
            log.info("=====================================");

            String theDyingMessage = String.format("Ciao, %s \n"
                    + "Grazie per la sua registrazione sull sito: https://bibixte.herokuapp.com/\n"
                    + "Per attivare il suo account manca l'ultimo step, cioe confermare l'email seguendo link sotto:\n"
                    + "Link per attivare: https://bibixte.herokuapp.com/activate/%s",
                    clienti.getUserName(),
                    clienti.getCodiceDiAttivazione());

            mailSenderService.send(clienti.getEmail(), "Activation of account", theDyingMessage);
        }
    }

    @Override
    public void sendConfirmAcquistoMail(boolean DO_SEND_MAILS, Clienti clienti,
                                        Bibite bibita,
                                        Acquisti acquisti,
                                        Double importo,
                                        int quantita_bibite_acquistate,
                                        String data_acquisto) throws MessagingException {
            if (DO_SEND_MAILS) {
                Corrieri corriere = corrieriRepository.findByNome("Paolo");
                String marca_bibita = bibita.getMarca();

                log.info("=====================================");
                log.info("Clienti service imp was called");
                log.info("Email is: " + clienti.getEmail());
                log.info("User name is: " + clienti.getUserName());
                log.info("Code is: " + clienti.getCodiceDiAttivazione());
                log.info("=====================================");

                String theDyingMessage = String.format("Ciao, %s \n"
                                + "Grazie per il suo acquisto sull sito: https://bibixte.herokuapp.com/\n"
                                + "Il suo acquisto vera consegnato dall corriere:%s\n"
                                + " ",
                        clienti.getUserName(),
                        corriere.getNome()
                                + " "
                                + corriere.getCognome() + "\n"
                                + "Detagli sull acquisto effetuato:\n"
                                + "Quantita di bibite acquistate:" + quantita_bibite_acquistate + "\n"
                                + "Marca di bibita acquistata: " + marca_bibita + "\n"
                                + "Importo totale da pagare: " + importo + "\n")
                        + "Data acquisto: " + data_acquisto;

                mailSenderService.send(clienti.getEmail(), "Detagli sull Acquisto", theDyingMessage);
            }
    }

    @Override
    public boolean activateUser(String code) {
        Clienti clienti = clientiRepository.findByCodiceDiAttivazione(code);

        clienti.setCodiceDiAttivazione("ACTIVATED");
        clienti.setConto(50.00);
        clientiRepository.save(clienti);
        log.info("Client activated! " + clienti.getUserName());
        return true;
    }

    @Override
    public Double decreaseConto(Double importo) {
        String userName = CustomUserDetails.clienti.getUserName();
        Clienti clienti = clientiRepository.findByUserName(userName);
        Double conto = clienti.getConto();

        log.info(conto + " pre conto aggiornato");

        Double conto_aggiornato = conto - importo;

        log.info(conto + " post conto aggiornato");
        log.info("conto aggiornato: " + conto_aggiornato);

        clienti.setConto(conto_aggiornato);

        log.info(clienti.getUserName());
        clientiRepository.save(clienti);
        log.info(conto + "post set conto");

        return conto_aggiornato;
    }


}
