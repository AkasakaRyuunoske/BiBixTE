package BiBixTE.BiBixTE.Repository;

import BiBixTE.BiBixTE.Entity.Bibite;
import BiBixTE.BiBixTE.Entity.Corrieri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrieriRepository extends JpaRepository<Corrieri, Long> {}
