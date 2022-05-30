package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Clienti;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface AcquistiService {
    String processAcquisto(int quantita, Double conto,
                         Double importo, Model model,
                         String nome_bibita, Clienti cliente,
                         String conto_to_display);
}
