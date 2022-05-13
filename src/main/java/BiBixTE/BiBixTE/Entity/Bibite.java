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
    private Long id_bibite;
    private String marca;
    private double capacita;
    private String descrizione;
    private String quantita;
}
