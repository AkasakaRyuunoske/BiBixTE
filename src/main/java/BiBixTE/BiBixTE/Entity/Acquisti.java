package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Acquisti implements Serializable {
    @Id
    @SequenceGenerator(name = "Acquisti_sequence", sequenceName = "Acquisti_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Acquisti_sequence")
    private Long id_acquisto;

//    @Column(name = "id_cliente", nullable = false)
//    private Long id_cliente;
//
//    @Column(name = "id_bibita", nullable = false)
//    private Long id_bibita;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "data_aquisto", nullable = false)
    private String data_aquisto;

    @Column(name = "importo", nullable = false)
    private Double importo;

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

//    @Column(name = "id_cliente_fk", nullable = true)
//    private Long id_cliente_fk;
//
//    @Column(name = "id_bibita_fk", nullable = true)
//    private Long id_bibita_fk;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente_fk", referencedColumnName = "id_cliente")
    private Clienti clienti;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bibita_fk", referencedColumnName = "id_bibita")
    private Bibite bibite;

    public Acquisti(String data_aquisto, double importo, int quantita) {
        this.data_aquisto = data_aquisto;
        this.importo = importo;
        this.stock = quantita;
    }

    public Acquisti(String data_aquisto, double importo, int quantita, String descrizione) {
        this.data_aquisto = data_aquisto;
        this.importo = importo;
        this.stock = quantita;
        this.descrizione = descrizione;
    }

    public Acquisti(String data_aquisto, double importo, int quantita, String descrizione, Long id_bibita, Long id_cliente) {
        this.data_aquisto = data_aquisto;
        this.importo = importo;
        this.stock = quantita;
        this.descrizione = descrizione;
//        this.id_cliente_fk = id_cliente;
//        this.id_bibita_fk = id_bibita;

        log.info("Acquisto created!");
    }
}
