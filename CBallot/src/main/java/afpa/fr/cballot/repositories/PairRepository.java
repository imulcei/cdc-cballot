package afpa.fr.cballot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Pair;

@Repository
public interface PairRepository extends JpaRepository<Pair, Integer> {

}
