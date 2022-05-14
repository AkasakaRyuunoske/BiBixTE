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
    @Column(name = "indirizzo_partenza", nullable = true)
    private String indirizzo_partenza;

    @Column(name = "indirizzo_arrivo", nullable = true)
    private String indirizzo_arrivo;

    @Column(name = "tempo_previsto", nullable = true)
    private int tempo_previsto;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_cliente_fk", referencedColumnName = "id_cliente")
//    List<Clienti> clienti;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_bibita_fk", referencedColumnName = "id_bibita")
//    List<Bibite> bibite;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_corriere_fk", referencedColumnName = "id_corriere")
//    List<Corrieri> corrieri;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente_fk", referencedColumnName = "id_cliente")
    private Clienti clienti;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bibita_fk", referencedColumnName = "id_bibita")
    private Bibite bibite;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_corriere_fk", referencedColumnName = "id_corriere")
    private Corrieri corrieri;
}
