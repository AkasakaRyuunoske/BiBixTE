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
    private Long id_cliente;
    private Long id_bibita;
    private int stock;
    private Date data_aquisto;
    private Double importo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente_fk", referencedColumnName = "id_cliente")
    List<Clienti> clienti;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bibita_fk", referencedColumnName = "id_bibita")
    List<Bibite> bibite;
}
