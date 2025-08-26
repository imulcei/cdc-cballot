package afpa.fr.cballot.repositories;

import java.util.Optional;
// import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository <Teacher, UUID> {
    Optional<Teacher> findByEmail(String email);

    boolean existsByEmail(String email);


}
