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
public class Clienti {
    @Id
    @SequenceGenerator(
            name = "Clienti_sequence",
            sequenceName = "Clienti_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Clienti_sequence"
    )
    private Long id_cliente;
    private String user_name;
    private String email;
    private String password;
    private Double conto;
}
