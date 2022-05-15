package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.model.ClientiModel;

public interface ClientiService {
    Clienti registerClienti(ClientiModel clientiModel);

    void saveVerificationTokneForClienti(String token, Clienti clienti);
}
