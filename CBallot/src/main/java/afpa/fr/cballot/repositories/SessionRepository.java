package afpa.fr.cballot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Session;

@Repository
public interface SessionRepository extends JpaRepository <Session, Integer> {
    
}
