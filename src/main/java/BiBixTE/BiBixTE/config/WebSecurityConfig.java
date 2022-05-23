package BiBixTE.BiBixTE.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        // temporally is not needed, previous use was in the SecurityFilterChain
        // if SecurityFilterChain will be needed this is needed too
//    private static final String[] WHITE_LIST_URLS = {
//            "/registration",
//            "/registration-complete",
//            "/",
//            "/login",
//            "/greeting",
//            "/activate/*"
//    };

    private static final  String[] UN_ACCESSIBLE_URLS_BY_NOT_AUTHENTICATED_USERS = {
            "/",
            "/greeting",
            "/crediti",
            "/acquisti",
            "/prodotti",
            "/acquista/**",
            "/acquista/***",
            "/acquista/*/*/*",
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .antMatchers(WHITE_LIST_URLS)
//                .permitAll();
//
//        return httpSecurity.build();
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/")
//                .permitAll()
//                .antMatchers("/registration")
//                .permitAll()
//                .antMatchers("/registration-complete")
//                .permitAll()
//                .antMatchers("/activate/*")
//                .permitAll()
//                .antMatchers("/admin")
//                .hasAuthority("AKASAKA")
                .antMatchers(UN_ACCESSIBLE_URLS_BY_NOT_AUTHENTICATED_USERS)
                .authenticated()
                .and()
                .formLogin()

                //to define custom login page, for some reason does not work
                //may be needed in the future

//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//todo                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");;
    }
}