package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.ClientiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.util.UUID;

@Slf4j
@Controller
public class RegistrationController {
    @Autowired
    private ClientiRepository clientiRepository;

    @Autowired
    private ClientiService clientiService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registration-complete")
    public String registration(@ModelAttribute Clienti formData, Model model) throws MessagingException {
        Clienti clienti = clientiRepository.findByUserName(formData.getUserName());

        if (clienti != null){
            return "Error_Templates/err-registration";
        }

        formData.setCodiceDiAttivazione(UUID.randomUUID().toString());
        clientiService.sendConfirmMail(formData);

            clientiRepository.save(new Clienti(formData.getUserName(),
                    formData.getEmail(),
                    passwordEncoder.encode(formData.getPassword()),
                    formData.getCodiceDiAttivazione()));

        model.addAttribute("formData", formData);

        return "result";
    }

    @GetMapping("/registration")
    public String registerClienti(@ModelAttribute Clienti cliente, Model model){
        model.addAttribute("cliente", cliente);

        return "registrazione.html";
    }

    @GetMapping("/activate/{code}")
    public String activateCode(Model model, @PathVariable String code){

        log.info("Code in activate code: " + code);
        boolean isActivated = clientiService.activateUser(code);

        if (isActivated){
            model.addAttribute("message","Utente e' attivato con successo!");
        } else {
            model.addAttribute("message","Attivazione ha incontrato degli errori :(");
        }
        
        return "activate";
    }
}
