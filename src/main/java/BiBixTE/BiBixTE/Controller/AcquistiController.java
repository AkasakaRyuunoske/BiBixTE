package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Bibite;
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
        Bibite bibita = bibiteRepository.findByMarca(nome_bibita);

        // model data
        String conto_to_display = "Your count: " + clienti.getConto().toString() + "€";
        String userName = clienti.getUserName();
        String background = "/" + bibita.getImagine();

        if (quantita == 0) {

            return "Error_Templates/err-product";

        } else {

            model.addAttribute("background_image", background);
            model.addAttribute("conto", conto_to_display);
            model.addAttribute("userName", userName);
            model.addAttribute("marca", nome_bibita);
        }

        return "confirm";
    }

    @GetMapping("/acquista/{nome_bibita}/{importo}/{quantita}/{indirizzo}")
    public String aquistiBibitaPOST(Model model,
                                   @PathVariable String nome_bibita,
                                   @PathVariable double importo,
                                   @PathVariable int quantita,
                                   @PathVariable String indirizzo,
                                   HttpServletRequest httpServletRequest){
        return acquistiServiceImp.processAcquisto(
                quantita,
                importo, model,
                nome_bibita,
                indirizzo,
                httpServletRequest);
    }
}
