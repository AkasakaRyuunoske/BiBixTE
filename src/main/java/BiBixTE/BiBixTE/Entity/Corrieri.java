package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Corrieri {
    private Long id_corriere;
    private String nome;
    private String cognome;
    private Date data_di_nascita;
    private String telefono;
}
