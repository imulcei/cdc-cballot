package afpa.fr.cballot.web.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.dtos.CourseDTO;
import afpa.fr.cballot.dtos.TeacherCourseDTO;
import afpa.fr.cballot.dtos.TeacherDTO;
import afpa.fr.cballot.services.CourseService;
import afpa.fr.cballot.services.TeacherService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/courses")
public class FormationRestController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public FormationRestController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    /**
     * GetAllCourseWithTeachers
     * 
     * @return
     * 
     *         retourne la liste de formations et la liste de formateurs
     */
    @GetMapping
    public ResponseEntity<?> getAllCourseWithTeachers() {
        List<CourseDTO> courses = courseService.getAllCourses();
        List<TeacherDTO> teachers = teacherService.getAllTeachers();

        TeacherCourseDTO data = new TeacherCourseDTO();

        data.setCourses(courses);
        data.setTeachers(teachers);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * GetTeachersForThisCourse
     * 
     * @param id
     * @return
     * 
     *         Retourne la liste de formateurs pour une formation selon ID
     */
    @GetMapping("/{id}/teachers")
    public ResponseEntity<List<TeacherDTO>> getTeachersForThisCourse(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(teacherService.getTeachersForOneCourse(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * CreateOneTeacher
     * 
     * @param dto
     * @return
     *         Créé un formateur
     */
    @PostMapping("/teachers")
    public ResponseEntity<?> createOneTeacher(@RequestBody TeacherDTO dto) {
        return new ResponseEntity<>(teacherService.createTeacher(dto), HttpStatus.CREATED);
    }

    /**
     * CreateTeacherAndAddToCourse
     * 
     * @param id
     * @param teacher
     * @return
     * 
     *         Créé un formateur et le lie à une formation
     */
    @PostMapping("/{id}/teachers")
    public ResponseEntity<?> createTeacherAndAddToCourse(@PathVariable Integer id, @RequestBody TeacherDTO teacher) {
        TeacherDTO created = teacherService.createTeacher(teacher);
        courseService.addTeacherToCourse(id, created.getId());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * AddTeacherToCourse
     * 
     * @param courseId
     * @param teacherId
     * @return
     * 
     *         Ajoute un formateur à une formation
     */
    @PostMapping("/{courseId}/teachers/{teacherId}")
    public ResponseEntity<?> addTeacherToCourse(@PathVariable Integer courseId, @PathVariable UUID teacherId) {
        try {
            courseService.addTeacherToCourse(courseId, teacherId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * RemoveTeacherToCourse
     * 
     * @param courseId
     * @param teacherId
     * @return
     * 
     *         Retire un formateur d'une formation
     */
    @DeleteMapping("/{courseId}/teachers/{teacherId}")
    public ResponseEntity<?> removeTeacherToCourse(@PathVariable Integer courseId, @PathVariable UUID teacherId) {
        try {
            courseService.removeTeacherToCourse(courseId, teacherId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // ! Méthode inutile (mais au cas ou)
    // /**
    // * GetAllCourses
    // *
    // * @return
    // *
    // * Retourne la liste des formations
    // */
    // @GetMapping("/courses")
    // public ResponseEntity<List<CourseDTO>> getAllCourses() {
    // return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    // }

    // /**
    // * GetOneCourse
    // *
    // * @param id
    // * @return
    // *
    // * retourne une formation
    // */
    // @GetMapping("/{id}")
    // public ResponseEntity<CourseDTO> getOneCourse(@PathVariable Integer id) {
    // try {
    // return new ResponseEntity<>(courseService.getOneCourse(id), HttpStatus.OK);
    // } catch (EntityNotFoundException e) {
    // return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    // }
    // }
    // /**
    // * GetTeachers
    // *
    // * @return
    // *
    // * Retourne la liste des formateurs
    // */
    // @GetMapping("/teachers")
    // public ResponseEntity<List<TeacherDTO>> getTeachers() {
    // return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    // }

    // /**
    // * GetOneTeacher
    // *
    // * @param id
    // * @return
    // *
    // * Retourne un formateur selon ID
    // */
    // @GetMapping("/teachers/{id}")
    // public ResponseEntity<TeacherDTO> getOneTeacher(@PathVariable UUID id) {
    // return new ResponseEntity<>(teacherService.getOneTeacher(id), HttpStatus.OK);
    // }
}
