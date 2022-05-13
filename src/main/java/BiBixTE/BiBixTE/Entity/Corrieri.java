package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private String nome;
    private String cognome;
    private Date data_di_nascita;
    private String telefono;
}
