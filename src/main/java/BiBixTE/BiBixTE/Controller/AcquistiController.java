package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.AcquistiRepository;
import BiBixTE.BiBixTE.Repository.BibiteRepository;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.AcquistiServiceImp;
import BiBixTE.BiBixTE.Service.ClientiServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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
                                   @PathVariable int quantita,
                                   HttpServletRequest httpServletRequest){
        Clienti clienti = clientiServiceImp.getClientBySession(httpServletRequest);

        String conto_to_display = "Your count: " + clienti.getConto().toString() + "€";
        String userName = clienti.getUserName();

        Double conto = clienti.getConto();

        return acquistiServiceImp.processAcquisto(
                quantita, conto,
                importo, model,
                nome_bibita, clienti,
                conto_to_display,
                userName,  httpServletRequest);
    }

    @PostMapping("/acquisti")
    public String aquistiPOST(){
        return "acquisti";
    }
}
