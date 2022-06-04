package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.*;
import BiBixTE.BiBixTE.Repository.AcquistiRepository;
import BiBixTE.BiBixTE.Repository.BibiteRepository;
import BiBixTE.BiBixTE.Repository.ConsegneRepository;
import BiBixTE.BiBixTE.Repository.CorrieriRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains all service logic methods that are used by @AcquistiController.
 *
 *
 * */

@Slf4j
@Service
public class AcquistiServiceImp implements AcquistiService{

    @Autowired
    AcquistiRepository  acquistiRepository;
    @Autowired
    BibiteRepository    bibiteRepository;
    @Autowired
    ConsegneRepository  consegneRepository;
    @Autowired
    CorrieriRepository corrieriRepository;

    @Autowired
    ClientiServiceImp   clientiServiceImp;
    @Autowired
    BibiteServiceImp    bibiteServiceImp;

    /**
     * The main method of this class is used to process each Acquisto(Purchase)
     * that comes from @AcquistiController, which receives parameters from the same.
     *
     * What it does is: Checks if any quantity of product is selected, if not dedicated
     * error page is returned. Than it checks if client has enough to pay the purchase he
     * is making, if no problem than it tries to create new Aquisto(Purchase) passing in
     * some info, decreases client's count, decreases count of products selected and sends
     * confirm mail to the client with info about Acquisto(Purchase).
     *
     * The model is changed from here and NOT from @AcquistiController, like all others
     * pages. In this only case Controller is used as it must be used, just to retrieve
     * info from user, send the info here, to @Service and than return result of elaboration
     * made in @Service.
     *
     * Error managing is simple, without using external classes for errors, it's managed
     * by just returning dedicated page for every error.
     *
     * */

    @Override
    public String processAcquisto(int quantita,
                                  double importo,
                                  Model model,
                                  String nome_bibita,
                                  String indirizzo,
                                  HttpServletRequest httpServletRequest) {

        //todo turn true
        final boolean DO_SEND_MAILS = false;

        // object is created to be used after, it must be seen for all method context
        Acquisti acquisti;
        Consegne consegne;
        Clienti cliente;

        // date formatter with european date pattern to save time when acquisto(purchase) was made
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String data_acquisto = dateTimeFormatter.format(LocalDateTime.now());

        cliente = clientiServiceImp.getClientBySession(httpServletRequest);
        String userName = cliente.getUserName();
        Double conto = cliente.getConto();
        String conto_to_display = "Your count: " + conto.toString() + "€";

        // must be before any checks because returned pages are different,
        // so it's easier to add default info regardless page returned
        model.addAttribute("conto", conto_to_display);
        model.addAttribute("userName", userName);

        // returns error page if quantita(quantity) is equal to 0 (no products selected)
        if (quantita == 0) {
            log.info("No pepsi? :(");
            return "Error_Templates/err-product";
        }

        // as long as purchase of more than one product is not possible, this works fine
        // so the desired product is found by his unique name
        Bibite bibita = bibiteRepository.findByMarca(nome_bibita);

        // description is updated with info about product purchased
        String acquisti_descrizione = "Prodotti acquistati: " + bibita.getMarca();

        // model background will be changed according to product name
        String background = "/" + bibita.getImagine();

        try {
            if (conto > importo) {
                acquisti = new Acquisti(
                        data_acquisto,
                        importo,
                        quantita,
                        acquisti_descrizione,
                        bibita,
                        cliente);

                clientiServiceImp.decreaseConto(cliente, importo);
                bibiteServiceImp.decreaseQuantity(quantita, bibita);

                Corrieri corrieri = corrieriRepository.findByNome("Paolo");
                consegne = new Consegne("Via Dante 16",
                        indirizzo,
                        10,
                        cliente,
                        bibita,
                        corrieri);

                consegneRepository.save(consegne);
                bibiteRepository.save(bibita);
                acquistiRepository.save(acquisti);

                model.addAttribute("background_image", background);

            } else {
                // if client's count is less then products cost this page is returned
                return "Error_Templates/err-balance";
            }
            if (!cliente.getEmail().isEmpty()) {
                clientiServiceImp.sendConfirmAcquistoMail(DO_SEND_MAILS, cliente, bibita,
                        acquisti, importo,
                        quantita, data_acquisto);
            } else {
                return "Error_Templates/err-general";
            }
        } catch (Exception exception) {
            return "Error_Templates/err-general";
        }

        return "confirm";
    }
}
