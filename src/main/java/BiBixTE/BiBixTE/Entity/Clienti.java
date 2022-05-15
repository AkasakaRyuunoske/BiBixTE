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

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "conto", nullable = true)
    private Double conto;

    //Will be used for registration
    public Clienti(String user_name, String email, String password){
        this.user_name = user_name;
        this.email = email;
        this.password = password;
    }
}
