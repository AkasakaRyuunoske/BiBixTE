package BiBixTE.BiBixTE.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientiModel {
    private String userName;
    private String email;
    private String password;
    private Double conto;
    private String activationCode;
}
