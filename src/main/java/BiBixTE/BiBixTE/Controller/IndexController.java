package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.ClientiDetailsService;
import BiBixTE.BiBixTE.Service.CustomUserDetails;
import BiBixTE.BiBixTE.Service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){

        String userName = CustomUserDetails.clienti.getUserName();
        String conto = "Your count: " + clientiRepository.findByUserName(userName).getConto().toString() + "€";
        Clienti clienti = clientiRepository.findByUserName(userName);

        String currentSession = request.getSession().getId();

        if (clienti.getSessionID() == null){

            log.info("currentSession : " + currentSession);
            log.info("clientiRepository.getSessionPrimaryIDBySessionID(currentSession)" + clientiRepository.getSessionPrimaryIDBySessionID(currentSession));
            clienti.setSessionID(clientiRepository.getSessionPrimaryIDBySessionID(currentSession));

            clientiRepository.save(clienti);
        }

        log.info("userName: " + userName);
        log.info("conto: " + conto);
        log.info("Session getSession" + request.getSession().getId());
        String  sessionID = clientiRepository.getSessionPrimaryIDBySessionID(currentSession);
        clienti = clientiRepository.findBySessionID(sessionID);
        String user_name_to_display = clienti.getUserName();

        model.addAttribute("userName", user_name_to_display);
        model.addAttribute("conto", conto);


        return "index.html";
    }

    @PostMapping("/")
    public String indexPost(@ModelAttribute Clienti formData, Model model){
        log.info("Form data: " + formData.getUserName());

        clientiDetailsService.loadUserByUsername(formData.getUserName());

        return "index.html";
    }

    @GetMapping("/crediti")
    public String crediti(Model model, HttpServletRequest request){

        String userName = CustomUserDetails.clienti.getUserName();
        String conto = "Your count: " + clientiRepository.findByUserName(userName).getConto().toString() + "€";

        model.addAttribute("userName", userName);
        model.addAttribute("conto", conto);

        log.info("Session getSession" + request.getSession().getId());

        return "crediti.html";
    }
}
