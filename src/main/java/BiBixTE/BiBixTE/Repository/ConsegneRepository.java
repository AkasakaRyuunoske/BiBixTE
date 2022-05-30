package BiBixTE.BiBixTE.Repository;

import BiBixTE.BiBixTE.Entity.Consegne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsegneRepository extends JpaRepository<Consegne, Long> {
}
