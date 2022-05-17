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

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "conto", nullable = true)
    private Double conto = 50.00;

    @Column(name = "activationCode", nullable = true)
    private String activationCode;

    //Will be used for registration
    public Clienti(String userName, String email, String password, String activationCode){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.activationCode = activationCode;
    }
}
