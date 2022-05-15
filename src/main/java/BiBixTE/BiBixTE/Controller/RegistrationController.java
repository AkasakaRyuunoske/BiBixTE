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

    @PostMapping("/registration")
    public String registration(@ModelAttribute Clienti formData, Model model){
        System.out.println("Hello world!");
        clientiRepository.save(new Clienti(formData.getUser_name(), formData.getEmail(), passwordEncoder.encode(formData.getPassword())));
        log.info("matching pass result : " +
                passwordEncoder
                        .matches("pussy_destroer2022",
                                "$2a$11$zl00U7ztEJaMo6MNIB9TW.gQnwIMVkYzOpl5DwIUV9LWpCJOWe6Ji"));
        model.addAttribute("formData", formData);
        return "result";
    }

    @PostMapping("/registration1")
    public String registerClienti(@RequestBody ClientiModel clientiModel, final HttpServletRequest request){
        Clienti clienti = clientiService.registerClienti(clientiModel);

        applicationEventPublisher.publishEvent(new RegistrationComplete(clienti,applicationUrl(request)));
        return "Success!";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @GetMapping("/")
    public String index(@ModelAttribute Clienti clienti, Model model){
        model.addAttribute("cliente", new Clienti());
        System.out.println("index called");
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute Clienti clienti, Model model){
        model.addAttribute("cliente", new Clienti());
        System.out.println("login");
        return "login";
    }
    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestHeader("accept-language") String language) {
        return new ResponseEntity<String>("greeting", HttpStatus.OK);
    }
}
