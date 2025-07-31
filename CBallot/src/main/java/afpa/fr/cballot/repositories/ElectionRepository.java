package afpa.fr.cballot.repositories;

import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Election;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Integer> {

}
