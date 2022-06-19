package BiBixTE.BiBixTE.Repository;

import BiBixTE.BiBixTE.Entity.Clienti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientiRepository extends JpaRepository<Clienti, Long> {
    Clienti findByUserName(String userName);

    Clienti findByCodiceDiAttivazione(String code);

    Clienti findBySessionID(String sessionID);


    /**
     *
     * Session management is here just because i cannot find any better suitable solution,
     * so i made a custom one.
     *
     * */
    @Query(value = "SELECT PRIMARY_ID FROM SPRING_SESSION s where s.SESSION_ID = :sessionID",
            nativeQuery = true)
    String getSessionPrimaryIDBySessionID(String sessionID);
}
