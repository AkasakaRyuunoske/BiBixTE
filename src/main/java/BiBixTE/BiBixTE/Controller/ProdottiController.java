package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ProdottiController {
    @Autowired
    ClientiRepository clientiRepository;

    @GetMapping("/prodotti")
    public String aquistiGet(Model model){

        String userName = CustomUserDetails.clienti.getUserName();
        String conto = "Your count: " + clientiRepository.findByUserName(userName).getConto().toString() + "€";

        model.addAttribute("userName", userName);
        model.addAttribute("conto", conto);

        log.info("userName: " + userName);
        log.info("conto: " + conto);

        return "prodotti";
    }

    @PostMapping("/prodotti")
    public String aquistiPost(){
        return "prodotti";
    }
}
