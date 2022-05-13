package BiBixTE.BiBixTE.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bibite {
    private Long id_bibite;
    private String marca;
    private double capacita;
    private String descrizione;
    private String quantita;
}
