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
public class Corrieri {
    @Id
    @SequenceGenerator(
            name = "Corrieri_sequence",
            sequenceName = "Corrieri_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Corrieri_sequence"
    )
    private Long id_corriere;

    @Column(name = "nome", nullable = true)
    private String nome;

    @Column(name = "cognome", nullable = true)
    private String cognome;

    @Column(name = "telefono", nullable = true)
    private String telefono;
}
