package afpa.fr.cballot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository <Student, Integer> {
    
}
