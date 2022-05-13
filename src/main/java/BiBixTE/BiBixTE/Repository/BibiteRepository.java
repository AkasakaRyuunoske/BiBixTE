package BiBixTE.BiBixTE.Repository;

import BiBixTE.BiBixTE.Entity.Bibite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibiteRepository extends JpaRepository<Bibite, Long> {}