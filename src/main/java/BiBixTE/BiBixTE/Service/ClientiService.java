package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface ClientiService {
    void registerClienti(Clienti clienti) throws MessagingException;

    boolean activateUser(String code);

    Double decreaseConto(Double importo);
}
