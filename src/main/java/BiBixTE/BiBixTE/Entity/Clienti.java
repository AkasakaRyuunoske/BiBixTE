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
public class Clienti {
    private Long id_cliente;
    private String user_name;
    private String email;
    private String password;
    private Double conto;
}
