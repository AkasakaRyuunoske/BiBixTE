package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.ClientiService;
import BiBixTE.BiBixTE.event.RegistrationComplete;
import BiBixTE.BiBixTE.model.ClientiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public String registration(@ModelAttribute Clienti formData, Model model){
        System.out.println("Hello world!");
        clientiRepository.save(new Clienti(formData.getUser_name(), formData.getEmail(), passwordEncoder.encode(formData.getPassword())));
        log.info("matching pass result : " +
                passwordEncoder
                        .matches("pussy_destroer2022",
                                "$2a$11$zl00U7ztEJaMo6MNIB9TW.gQnwIMVkYzOpl5DwIUV9LWpCJOWe6Ji"));
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

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
