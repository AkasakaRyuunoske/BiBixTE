package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consegne {
    private Long id_consegna;
    private Long id_cliente;
    private Long id_bibita;
    private Long id_corriere;
    private String indirizzo_partenza;
    private String indirizzo_arrivo;
    private int tempo_previsto;
}
