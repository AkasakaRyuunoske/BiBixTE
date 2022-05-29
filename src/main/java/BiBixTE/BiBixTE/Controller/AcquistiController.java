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
import java.time.LocalTime;
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
        Long id_cliente = clientiRepository.findByUserName(userName).getId_cliente();

        if (quantita == 0){
            log.info("No pepsi? :(");
            model.addAttribute("NoProductSelectedError",
                    "Nessun prodotto selezionato.");
            return "acquisti";
        }

        Bibite bibita = bibiteRepository.findByMarca(nome_bibita);
        String acquisti_descrizione = "Prodotti acquistati: " + bibita.getMarca();

        model.addAttribute("marca", nome_bibita);
        model.addAttribute("descrizione", bibita.getDescrizione());
        model.addAttribute("capacita", bibita.getCapacita());
        model.addAttribute("quantita",quantita);
        model.addAttribute("importo", importo);
        model.addAttribute("userName", userName);

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String data_acquisto = dateTimeFormatter.format(LocalDateTime.now());

            Acquisti acquisti = new Acquisti(
                    data_acquisto,
                    importo,
                    quantita,
                    acquisti_descrizione,
                    bibita.getId_bibita(),
                    id_cliente
                    );

            Clienti cliente = clientiRepository.findByUserName(userName);
            Double conto = cliente.getConto();

            if (conto > importo) {

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
                return "err-general";
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
        log.info("time: " + LocalTime.now().toString());
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

        return "acquisti";
    }

    @PostMapping("/acquisti")
    public String aquistiPOST(){
        return "acquisti";
    }
}
