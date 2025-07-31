package afpa.fr.cballot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository <Admin,UUID> {
}
