package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class IndexController {
    @Autowired
    MailSender mailSender;

    @GetMapping("/")
    public String index(@ModelAttribute Clienti clienti, Model model){
        model.addAttribute("cliente", new Clienti());
        System.out.println("index called");
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
