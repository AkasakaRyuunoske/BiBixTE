package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;

import javax.mail.MessagingException;

public interface ClientiService {
    void registerClienti(Clienti clienti) throws MessagingException;

    boolean activateUser(String code);
}
