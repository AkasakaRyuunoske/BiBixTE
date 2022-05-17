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
import javax.servlet.http.HttpServletRequest;
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
        System.out.println("Hello world!");

        Clienti clienti = clientiRepository.findByUserName(formData.getUserName());

        formData.setActivationCode(UUID.randomUUID().toString());

        log.info("Activation code of form data at moment of registration client:" + formData.getActivationCode());
        clientiService.registerClienti(formData);

        if (clienti != null){
            return "clienteGiaEssiste";
        }

        clientiRepository.save(new Clienti(formData.getUserName(), formData.getEmail(), passwordEncoder.encode(formData.getPassword())));
//        log.info("matching pass result : " +
//                passwordEncoder
//                        .matches("pussy_destroer2022",
//                                "$2a$11$zl00U7ztEJaMo6MNIB9TW.gQnwIMVkYzOpl5DwIUV9LWpCJOWe6Ji"));
        model.addAttribute("formData", formData);

        //another way to make same thing, may be needed
//        Clienti clienti = clientiService.registerClienti(clientiModel);
//
//        applicationEventPublisher.publishEvent(new RegistrationComplete(clienti,applicationUrl(request)));

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


    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
