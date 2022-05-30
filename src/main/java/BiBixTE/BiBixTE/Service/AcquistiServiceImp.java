package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Acquisti;
import BiBixTE.BiBixTE.Entity.Bibite;
import BiBixTE.BiBixTE.Entity.Clienti;
import BiBixTE.BiBixTE.Repository.AcquistiRepository;
import BiBixTE.BiBixTE.Repository.BibiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class AcquistiServiceImp implements AcquistiService{
    @Autowired
    AcquistiRepository acquistiRepository;

    @Autowired
    BibiteRepository bibiteRepository;
    @Autowired
    ClientiServiceImp clientiServiceImp;

    public String processAcquisto(int quantita,
                                Double conto,
                                Double importo,
                                Model model,
                                String nome_bibita,
                                Clienti cliente,
                                String conto_to_display) {

        Acquisti acquisti;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String data_acquisto = dateTimeFormatter.format(LocalDateTime.now());

        if (quantita == 0) {
            log.info("No pepsi? :(");
            return "err-product";
        }

        Bibite bibita = bibiteRepository.findByMarca(nome_bibita);
        String acquisti_descrizione = "Prodotti acquistati: " + bibita.getMarca();
        String background = "/" + bibita.getImagine();

        model.addAttribute("marca", bibita.getMarca());
        model.addAttribute("descrizione", bibita.getDescrizione());
        model.addAttribute("capacita", bibita.getCapacita());
        model.addAttribute("quantita", quantita);
        model.addAttribute("importo", importo);
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

        log.info("=====================================");

        log.info("bibita getDescrizione: " + bibita.getDescrizione());
        log.info("bibita getMarca: " + bibita.getMarca());
        log.info("bibita getQuantita: " + bibita.getQuantita());
        log.info("bibita getCapacita: " + bibita.getCapacita());
        log.info("bibita getId_bibita: " + bibita.getId_bibita());

        log.info("=====================================");

        return "confirm";
    }
}
