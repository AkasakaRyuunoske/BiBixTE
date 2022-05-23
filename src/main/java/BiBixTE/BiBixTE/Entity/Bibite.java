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
public class Bibite {
    @Id
    @SequenceGenerator(
            name = "Bibite_sequence",
            sequenceName = "Bibite_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Bibite_sequence"
    )
    private Long id_bibita;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "capacita", nullable = false)
    private double capacita;

    @Column(name = "descrizione", nullable = true)
    private String descrizione;

    @Column(name = "quantita", nullable = false)
    private String quantita;

    @Column(name = "costo", nullable = false)
    private Double costo;
}
