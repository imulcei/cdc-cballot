package afpa.fr.cballot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Session;

@Repository
public interface SessionRepository extends JpaRepository <Session, Integer> {
    List<Session> findByCourseId(Integer id);
}
