package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Acquisti {
    private Long id_acquisto;
    private Long id_cliente;
    private Long id_bibita;
    private int stock;
    private Date data_aquisto;
    private Double importo;
}
