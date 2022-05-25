package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.ClientiDetailsService;
import BiBixTE.BiBixTE.Service.CustomUserDetails;
import BiBixTE.BiBixTE.Service.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

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
    public String index(Model model){

        String userName = CustomUserDetails.clienti.getUserName();
        String conto = "Your count: " + clientiRepository.findByUserName(userName).getConto().toString() + "€";

        model.addAttribute("userName", userName);
        model.addAttribute("conto", conto);

        log.info("userName: " + userName);
        log.info("conto: " + conto);
        return "index.html";
    }

    @PostMapping("/")
    public String indexPost(@ModelAttribute Clienti formData, Model model){
//        try {
//            Clienti client = clientiRepository.findByUserName(formData.getUserName());
//            if (!passwordEncoder.matches(formData.getPassword(), client.getPassword())){
//                log.info("does not matches");
//                return "clienteGiaEssiste";
//            }
//            model.addAttribute("clienti", client);
//
//            log.info("=====================================");
//            log.info("User name:" + client.getUserName());
//            log.info("Password:" + client.getPassword());
//            log.info("Password from data:" + formData.getPassword());
//            log.info("Email:" + client.getEmail());
//            log.info("=====================================");
//        } catch (NullPointerException nullPointerException){
//            return "clienteGiaEssiste";
//        }
        log.info("Form data: " + formData.getUserName());
        clientiDetailsService.loadUserByUsername(formData.getUserName());
        return "index.html";
    }


    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestHeader("accept-language") String language) {
        return new ResponseEntity<String>("greeting", HttpStatus.OK);
    }

    @GetMapping("/crediti")
    public String crediti(Model model){

        String userName = CustomUserDetails.clienti.getUserName();
        String conto = "Your count: " + clientiRepository.findByUserName(userName).getConto().toString() + "€";

        model.addAttribute("userName", userName);
        model.addAttribute("conto", conto);
        return "crediti.html";
    }
}
