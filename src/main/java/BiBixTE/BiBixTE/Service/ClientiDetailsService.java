package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientiDetailsService implements UserDetailsService {

    @Autowired
    private ClientiRepository clientiRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Clienti clienti = clientiRepository.findByUserName(username);

        if (clienti == null){
            throw new UsernameNotFoundException("Clinte non ritrovato o non esiste.");
        }

        return new CustomUserDetails(clienti);
    }
}
