package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.AcquistiRepository;
import BiBixTE.BiBixTE.Repository.BibiteRepository;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.AcquistiServiceImp;
import BiBixTE.BiBixTE.Service.ClientiServiceImp;
import BiBixTE.BiBixTE.Service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AcquistiController {
    @Autowired
    AcquistiRepository acquistiRepository;

    @Autowired
    BibiteRepository bibiteRepository;

    @Autowired
    ClientiRepository clientiRepository;

    @Autowired
    ClientiServiceImp clientiServiceImp;
    @Autowired
    AcquistiServiceImp acquistiServiceImp;

    @GetMapping("/acquista/{nome_bibita}/{importo}/{quantita}")
    public String aquistiBibitaGET(Model model,
                                   @PathVariable String nome_bibita,
                                   @PathVariable double importo,
                                   @PathVariable int quantita){

        String userName = CustomUserDetails.clienti.getUserName();
        String conto_to_display = "Your count: " + clientiRepository.findByUserName(userName).getConto().toString() + "€";
        Clienti cliente = clientiRepository.findByUserName(userName);

        Double conto = cliente.getConto();

        return acquistiServiceImp.processAcquisto(quantita,
                conto, importo,
                model, nome_bibita,
                cliente, conto_to_display);
    }

    @PostMapping("/acquisti")
    public String aquistiPOST(){
        return "acquisti";
    }
}
