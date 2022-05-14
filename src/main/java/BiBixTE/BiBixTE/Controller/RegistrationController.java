package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    @Autowired
    private ClientiRepository clientiRepository;

    @PostMapping("/registration")
    public String registration(@ModelAttribute Clienti formData, Model model){
        System.out.println("Hello world!");
        clientiRepository.save(new Clienti(formData.getUser_name(), formData.getEmail(), formData.getPassword()));
        model.addAttribute("formData", formData);
        return "result";
    }

    @GetMapping("/")
    public String index(){
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
