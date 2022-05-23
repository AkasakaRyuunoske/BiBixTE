package BiBixTE.BiBixTE.Repository;

import BiBixTE.BiBixTE.Entity.Acquisti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistiRepository extends JpaRepository<Acquisti, Long> {}
