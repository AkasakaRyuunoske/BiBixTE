package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.model.ClientiModel;

public interface ClientiService {
    Clienti registerClienti(Clienti clienti);

    void saveVerificationTokneForClienti(String token, Clienti clienti);

    boolean activateUser(String code);
}
