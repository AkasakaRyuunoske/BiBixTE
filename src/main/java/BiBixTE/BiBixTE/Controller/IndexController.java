package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.ClientiDetailsService;
import BiBixTE.BiBixTE.Service.ClientiServiceImp;
import BiBixTE.BiBixTE.Service.CustomUserDetails;
import BiBixTE.BiBixTE.Service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    ClientiRepository clientiRepository;
    @Autowired
    MailSenderService mailSenderService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ClientiDetailsService clientiDetailsService;
    @Autowired
    ClientiServiceImp clientiServiceImp;

    /**
     *
     *  Here is common entry point for every user so here
     *  unique session is set to a user following this steps:
     *
     *  1. Identify user by username and find him on repository
     *     then set found client to a local object.
     *
     *  2. Get current session and PRIMARY_ID of the same session.
     *
     *  3. If client does not have session, this means he's just
     *     redirected from login page, so sessionID is set and from
     *     now on user is identified by his unique sessionID.
     *
     *     Data (Username, Conto) is set accordingly.
     *
     *     If a client already has sessionID so he is identified by that.
     *
     *  First part is unique for Index page controller.
     *
     *
     * */

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){


        String userName = CustomUserDetails.clienti.getUserName();

        Clienti clienti = clientiRepository.findByUserName(userName);

        String currentSession = request.getSession().getId();
        final String SESSION_PRIMARY_ID = clientiRepository.getSessionPrimaryIDBySessionID(currentSession);


        String conto;
        String user_name_to_display;

        if (clienti.getSessionID() == null){

            clienti.setSessionID(SESSION_PRIMARY_ID);

            clientiRepository.save(clienti);

            clienti = clientiRepository.findBySessionID(SESSION_PRIMARY_ID);

        } else {
            clienti = clientiServiceImp.getClientBySession(request);
        }

        conto = "Your count: " + clienti.getConto().toString() + "€";
        user_name_to_display = clienti.getUserName();

        model.addAttribute("userName", user_name_to_display);
        model.addAttribute("conto", conto);

        return "index.html";
    }

    @GetMapping("/crediti")
    public String crediti(Model model, HttpServletRequest request){

        String currentSession = request.getSession().getId();
        final String SESSION_PRIMARY_ID = clientiRepository.getSessionPrimaryIDBySessionID(currentSession);

        Clienti clienti = clientiRepository.findBySessionID(SESSION_PRIMARY_ID);

        String user_name_to_display = clienti.getUserName();
        String conto = "Your count: " + clienti.getConto().toString() + "€";

        model.addAttribute("userName", user_name_to_display);
        model.addAttribute("conto", conto);

        return "crediti.html";
    }
}
