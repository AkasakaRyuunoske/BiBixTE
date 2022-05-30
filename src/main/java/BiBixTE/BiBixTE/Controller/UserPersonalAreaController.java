package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.AcquistiRepository;
import BiBixTE.BiBixTE.Repository.BibiteRepository;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.ClientiServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class UserPersonalAreaController {
    @Autowired
    AcquistiRepository acquistiRepository;

    @Autowired
    BibiteRepository bibiteRepository;

    @Autowired
    ClientiRepository clientiRepository;

    @Autowired
    ClientiServiceImp clientiServiceImp;

    @GetMapping("/clienti/{userName}")
    public String personalArea(@PathVariable String userName, Model model){
        Clienti clienti = clientiRepository.findByUserName(userName);
        String email = clienti.getEmail();
        String conto = "Your count: " + clientiRepository.findByUserName(userName).getConto().toString() + "€";

        model.addAttribute("conto",conto);
        model.addAttribute("email", email);


        return "account";
    }
}
