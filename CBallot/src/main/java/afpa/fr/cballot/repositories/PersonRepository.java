package afpa.fr.cballot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository <Person, Integer> {
    
}
