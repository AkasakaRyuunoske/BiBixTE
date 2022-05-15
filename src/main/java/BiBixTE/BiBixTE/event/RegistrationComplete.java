package BiBixTE.BiBixTE.event;

import BiBixTE.BiBixTE.Entity.Clienti;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationComplete extends ApplicationEvent {

    private Clienti clienti;
    private String applicationUrl;

    public RegistrationComplete(Clienti clienti, String applicationUrl) {
        super(clienti);
        this.clienti = clienti;
        this.applicationUrl = applicationUrl;
    }
}
