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

    public CustomUserDetails(Clienti clienti) {
        //super();
        this.clienti = clienti;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(clienti.getRuolo()));
    }

    @Override
    public String getPassword() {
        return clienti.getPassword();
    }


    @Override
    public String getUsername() {
        // The check is here for not authenticated users, if one does try
        // to do anything exception is thrown

        if (clienti == null){
            return "unregistered";
        } else {
            return clienti.getUserName();
        }
    }

    // Must be here because are part of the implemented interface, even if are not used properly

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
