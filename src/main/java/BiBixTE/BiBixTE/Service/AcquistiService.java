package BiBixTE.BiBixTE.Service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Service
public interface AcquistiService {

    /**
     * Will be used in @AcquistiServiceImp to process Acquisto(means Purchase)
     * receiving these parameters from @AcquistiController.
     *
     * This method is even receiving Model model thus it adds attributes
     * to the model returned by @AcquistoController
     *
     * */

//    String processAcquisto(int quantita, Double conto,
//                           Double importo, Model model,
//                           String nome_bibita, Clienti cliente,
//                           String conto_to_display,
//                           String userName,
//                           HttpServletRequest httpServletRequest);

    String processAcquisto(int quantita,
                           double importo,
                           Model model,
                           String nome_bibita,
                           String indirizzo,
                           HttpServletRequest httpServletRequest);
}
