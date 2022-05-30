package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Bibite;
import org.springframework.stereotype.Service;

@Service
public class BibiteServiceImp implements BibiteService{

    public int decreaseQuantity(int quantita, Bibite bibita) {
        int starting_quantita = bibita.getQuantita();
        int actual_quantity = starting_quantita - quantita;

        bibita.setQuantita(actual_quantity);

        return actual_quantity;
    }
}
