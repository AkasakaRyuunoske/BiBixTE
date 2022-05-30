package BiBixTE.BiBixTE.Service;

import BiBixTE.BiBixTE.Entity.Bibite;
import org.springframework.stereotype.Service;

@Service
public interface BibiteService {
    int decreaseQuantity(int quantita, Bibite bibita);
}
