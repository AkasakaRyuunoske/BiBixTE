package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(name = "indirizzo_partenza", nullable = true)
    private String indirizzo_partenza;

    @Column(name = "indirizzo_arrivo", nullable = true)
    private String indirizzo_arrivo;

    @Column(name = "tempo_previsto", nullable = true)
    private int tempo_previsto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente_fk", referencedColumnName = "id_cliente")
    private Clienti clienti;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bibita_fk", referencedColumnName = "id_bibita")
    private Bibite bibite;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_corriere_fk", referencedColumnName = "id_corriere")
    private Corrieri corrieri;

    public Consegne(String indirizzo_partenza, String indirizzo_arrivo, int tempo_previsto, Clienti cliente, Bibite bibita, Corrieri corrieri) {
        this.indirizzo_partenza = indirizzo_partenza;
        this.indirizzo_arrivo = indirizzo_arrivo;
        this.tempo_previsto = tempo_previsto;
        setClienti(cliente);
        setBibite(bibita);
        setCorrieri(corrieri);
    }
}
