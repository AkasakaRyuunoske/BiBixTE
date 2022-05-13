package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Acquisti {
    @Id
    @SequenceGenerator(
            name = "Acquisti_sequence",
            sequenceName = "Acquisti_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Acquisti_sequence"
    )
    private Long id_acquisto;

    @Id
    @SequenceGenerator(
            name = "Cliente_sequence",
            sequenceName = "Cliente_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Cliente_sequence"
    )
    private Long id_cliente;

    @Id
    @SequenceGenerator(
            name = "Bibita_sequence",
            sequenceName = "Bibita_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Bibita_sequence"
    )
    private Long id_bibita;
    private int stock;
    private Date data_aquisto;
    private Double importo;
}
