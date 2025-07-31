package afpa.fr.cballot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Integer> {

}
