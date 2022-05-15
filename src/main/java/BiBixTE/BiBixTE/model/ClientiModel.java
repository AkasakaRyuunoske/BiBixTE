package BiBixTE.BiBixTE.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientiModel {
    private String user_name;
    private String email;
    private String password;
    private Double conto;
}
