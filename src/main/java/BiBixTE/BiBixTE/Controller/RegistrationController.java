package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @PostMapping("/registration")
    public String registration(@ModelAttribute Clienti clienti, Model model){
        System.out.println("Hello world!");
        model.addAttribute("clienti", new Clienti());
        return "index.html";
    }
}
