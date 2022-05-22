package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Slf4j
public class CustomUserDetails implements UserDetails {

    public static Clienti clienti;
    // maybe repeat every field of clienti @entity
    // but here is not needed

    public CustomUserDetails(Clienti clienti) {
        //super();
        this.clienti = clienti;
//        log.info("CustomUserDetails constructor getUserName: " + clienti.getUserName());
//        log.info("CustomUserDetails constructor getPassword: " + clienti.getPassword());
//        log.info("CustomUserDetails constructor getEmail: " + clienti.getEmail());
//        log.info("CustomUserDetails constructor getId_cliente: " + clienti.getId_cliente());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(clienti.getRuolo()));
    }

    @Override
    public String getPassword() {
//        log.info("Password: " + clienti.getPassword());
        return clienti.getPassword();
    }


    @Override
    public String getUsername() {
//        log.info("Username: " + clienti.getUserName());
        return clienti.getUserName();
    }

    // are not used but must return true in order to continue work
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
