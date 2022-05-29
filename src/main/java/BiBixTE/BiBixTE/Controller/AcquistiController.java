package BiBixTE.BiBixTE.Controller;

import BiBixTE.BiBixTE.Entity.Acquisti;
import BiBixTE.BiBixTE.Entity.Bibite;
import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.AcquistiRepository;
import BiBixTE.BiBixTE.Repository.BibiteRepository;
import BiBixTE.BiBixTE.Repository.ClientiRepository;
import BiBixTE.BiBixTE.Service.ClientiServiceImp;
import BiBixTE.BiBixTE.Service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @GetMapping("/acquista/{nome_bibita}/{importo}/{quantita}")
    public String aquistiBibitaGET(Model model,
                                   @PathVariable String nome_bibita,
                                   @PathVariable double importo,
                                   @PathVariable int quantita){

        String userName = CustomUserDetails.clienti.getUserName();
        String conto_to_display = "Your count: " + clientiRepository.findByUserName(userName).getConto().toString() + "€";
        Clienti cliente = clientiRepository.findByUserName(userName);

        Double conto = cliente.getConto();

        Acquisti acquisti;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String data_acquisto = dateTimeFormatter.format(LocalDateTime.now());

        if (quantita == 0){
            log.info("No pepsi? :(");
            return "err-product";
        }

        Bibite bibita = bibiteRepository.findByMarca(nome_bibita);
        String acquisti_descrizione = "Prodotti acquistati: " + bibita.getMarca();
        String background = "/" + bibita.getImagine();

        model.addAttribute("marca", bibita.getMarca());
        model.addAttribute("descrizione", bibita.getDescrizione());
        model.addAttribute("capacita", bibita.getCapacita());
        model.addAttribute("quantita",quantita);
        model.addAttribute("importo", importo);
        model.addAttribute("userName", userName);
        model.addAttribute("conto", conto_to_display);

        model.addAttribute("background_image", background);

        try {
            if (conto > importo) {
                acquisti = new Acquisti(
                        data_acquisto,
                        importo,
                        quantita,
                        acquisti_descrizione,
                        bibita,
                        cliente
                        );

                clientiServiceImp.decreaseConto(importo);

                int starting_quantita = bibita.getQuantita();
                bibita.setQuantita(starting_quantita - quantita);

                bibiteRepository.save(bibita);
                acquistiRepository.save(acquisti);

                log.info("Cliente" + cliente);
                log.info("bibita" + acquisti);
                log.info("importo" + importo);
                log.info("acquisti" + acquisti);

            } else {
                return "err-balance";
            }

            if (!cliente.getEmail().isEmpty()) {
                clientiServiceImp.sendConfirmAcquistoMail(cliente, bibita, acquisti, importo, quantita, data_acquisto);

            } else {
                return "err-general";
            }

        } catch (Exception exception) {
            return "err-general";
        }

        log.info("=====================================");

        log.info("Prodotto: " + nome_bibita);
        log.info("time: " + data_acquisto);
        log.info("importo: " + importo);
        log.info("quantita: " + quantita);
        log.info("userName: " + userName);

        log.info("=====================================");

        log.info("bibita getDescrizione: " + bibita.getDescrizione());
        log.info("bibita getMarca: " + bibita.getMarca());
        log.info("bibita getQuantita: " + bibita.getQuantita());
        log.info("bibita getCapacita: " + bibita.getCapacita());
        log.info("bibita getId_bibita: " + bibita.getId_bibita());

        log.info("=====================================");

        return "confirm";
    }

    @PostMapping("/acquisti")
    public String aquistiPOST(){
        return "acquisti";
    }
}
