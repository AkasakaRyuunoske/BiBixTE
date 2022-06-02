package BiBixTE.BiBixTE.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository {
    @Query(
            value = "SELECT PRIMARY_ID FROM spring_session s where s.SESSION_ID = :sessionID",
            nativeQuery = true
    )
    String getSessionPrimaryIDBySessionID(String sessionID);
}
