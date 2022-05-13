package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consegne {
    @Id
    @SequenceGenerator(
            name = "Consegne_sequence",
            sequenceName = "Consegne_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Consegne_sequence"
    )
    private Long id_consegna;
    private Long id_cliente;
    private Long id_bibita;
    private Long id_corriere;
    private String indirizzo_partenza;
    private String indirizzo_arrivo;
    private int tempo_previsto;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente_fk", referencedColumnName = "id_cliente")
    List<Clienti> clienti;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bibita_fk", referencedColumnName = "id_bibita")
    List<Bibite> bibite;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_corriere_fk", referencedColumnName = "id_corriere")
    List<Corrieri> corrieri;
}
