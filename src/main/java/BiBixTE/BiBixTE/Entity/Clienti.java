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
    private Double conto;

    @Column(name = "codiceDiAttivazione", nullable = true)
    private String codiceDiAttivazione;

    @Column(name = "ruolo", nullable = false)
    private String ruolo = "CLIENTE";

    @Column(name = "sessionID", nullable = true)
    private String sessionID;

    public Clienti(String userName, String email, String password, String codiceDiAttivazione){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.codiceDiAttivazione = codiceDiAttivazione;
    }

    public Clienti(String userName, String email, String password, String codiceDiAttivazione, String ruolo) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.codiceDiAttivazione = codiceDiAttivazione;
        this.ruolo = ruolo;
    }
}
