package afpa.fr.cballot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afpa.fr.cballot.entities.Course;
import afpa.fr.cballot.entities.Teacher;

@Repository
public interface CourseRepository extends JpaRepository <Course, Integer> {
    List<Teacher> findAllTeacherById(Integer id);
}
