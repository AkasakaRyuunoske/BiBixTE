package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Entity.VerificationToken;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Repository.VerificationTokenRepository;
import BiBixTE.BiBixTE.model.ClientiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientiServiceImp implements ClientiService{
    @Autowired
    private ClientiRepository clientiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public Clienti registerClienti(ClientiModel clientiModel) {
        Clienti clienti = new Clienti();

        clienti.setUser_name(clienti.getUser_name());
        clienti.setEmail(clientiModel.getEmail());
        clienti.setPassword(passwordEncoder.encode(clientiModel.getPassword()));

        clientiRepository.save(clienti);
        return clienti;
    }

    @Override
    public void saveVerificationTokneForClienti(String token, Clienti clienti) {
        VerificationToken verificationToken = new VerificationToken(clienti, token);

        verificationTokenRepository.save(verificationToken);
    }

}
