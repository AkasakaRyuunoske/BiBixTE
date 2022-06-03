package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Acquisti;
import BiBixTE.BiBixTE.Entity.Bibite;
import BiBixTE.BiBixTE.Entity.Clienti;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Service
public interface ClientiService {

    void sendConfirmMail(Clienti clienti) throws MessagingException;

    void sendConfirmAcquistoMail(boolean DO_SEND_MAILS, Clienti clienti,
                                 Bibite bibite,
                                 Acquisti acquisti,
                                 Double importo,
                                 int quantita_bibite_acquistate,
                                 String data_acquisto) throws MessagingException;

    boolean activateUser(String code);

    Double decreaseConto(Double importo);

    Clienti getClientBySession(HttpServletRequest httpServletRequest);
}
