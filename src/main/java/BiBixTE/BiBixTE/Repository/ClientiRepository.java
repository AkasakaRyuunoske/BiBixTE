package BiBixTE.BiBixTE.Repository;

import BiBixTE.BiBixTE.Entity.Clienti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientiRepository extends JpaRepository<Clienti, Long> {
    Clienti findByUserName(String userName);

    Clienti findByActivationCode(String code);
}
