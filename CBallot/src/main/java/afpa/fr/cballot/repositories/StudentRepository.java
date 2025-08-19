package afpa.fr.cballot.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository <Student, UUID> {
    List<Student> findAllBySessionId(Integer id);

    Optional<Student> findByIdAndSessionId(UUID id_student, Integer id_session);

    boolean existsBySessionIdAndId(Integer id_session, UUID id);
}
