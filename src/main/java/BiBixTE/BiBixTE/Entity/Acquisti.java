package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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
    private Date data_aquisto;

    @Column(name = "importo", nullable = false)
    private Double importo;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente_fk")
//    List<Clienti> clienti;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_bibita", referencedColumnName = "id_bibita_fk")
//    List<Bibite> bibite;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente_fk", referencedColumnName = "id_cliente")
    private Clienti clienti;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bibita_fk", referencedColumnName = "id_bibita")
    private Bibite bibite;
}
