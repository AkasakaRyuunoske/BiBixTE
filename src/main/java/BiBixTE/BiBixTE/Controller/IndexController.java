package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.MailSender;
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
    MailSender mailSender;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(@ModelAttribute Clienti clienti, Model model){
        model.addAttribute("clienti", new Clienti());
        System.out.println("index called");
        return "index.html";
    }

    @PostMapping("/")
    public String indexPost(@ModelAttribute Clienti formData, Model model){
        try {
            Clienti client = clientiRepository.findByUserName(formData.getUserName());
            if (passwordEncoder.matches(formData.getPassword(), client.getPassword())){
                log.info("matches");
            }
            model.addAttribute("clienti", client);

            log.info("=====================================");
            log.info("User name:" + client.getUserName());
            log.info("Password:" + client.getPassword());
            log.info("Password from data:" + formData.getPassword());
            log.info("Email:" + client.getEmail());
            log.info("=====================================");
        } catch (NullPointerException nullPointerException){
            return "clienteGiaEssiste";
        }
        return "index.html";
    }


    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestHeader("accept-language") String language) {
        return new ResponseEntity<String>("greeting", HttpStatus.OK);
    }

    @GetMapping("/crediti")
    public String crediti(){
        return "crediti.html";
    }
}
