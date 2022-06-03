package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.ClientiServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ProdottiController {
    @Autowired
    ClientiRepository clientiRepository;
    @Autowired
    ClientiServiceImp clientiServiceImp;

    @GetMapping("/prodotti")
    public String aquistiGet(Model model, HttpServletRequest httpServletRequest){

        Clienti clienti = clientiServiceImp.getClientBySession(httpServletRequest);

        String userName = clienti.getUserName();
        String conto = "Your count: " + clienti.getConto().toString() + "€";

        model.addAttribute("userName", userName);
        model.addAttribute("conto", conto);

        return "prodotti";
    }

    @PostMapping("/prodotti")
    public String aquistiPost(){
        return "prodotti";
    }
}
