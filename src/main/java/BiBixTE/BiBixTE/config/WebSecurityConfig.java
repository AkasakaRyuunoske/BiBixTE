package BiBixTE.BiBixTE.config;

import BiBixTE.BiBixTE.Service.ClientiDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EnableWebSecurity
@EnableJdbcHttpSession
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientiDetailsService clientiDetailsService;

    private static final  String[] INACCESSIBLE_URLS_BY_NOT_AUTHENTICATED_USERS = {
            "/",
            "/greeting",
            "/crediti",
            "/acquisti",
            "/prodotti",
            "/acquista/**",
            "/acquista/***",
            "/acquista/*/*/*",
            "/clienti/*"
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(clientiDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(INACCESSIBLE_URLS_BY_NOT_AUTHENTICATED_USERS)
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .sessionManagement()
                .maximumSessions(1);
    }
}