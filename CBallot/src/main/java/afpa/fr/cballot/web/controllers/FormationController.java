package afpa.fr.cballot.web.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.dtos.CourseDTO;
import afpa.fr.cballot.dtos.CourseWithTeachersDTO;
import afpa.fr.cballot.dtos.TeacherCourseDTO;
import afpa.fr.cballot.dtos.TeacherDTO;
import afpa.fr.cballot.services.CourseService;
import afpa.fr.cballot.services.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/courses")
public class FormationController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public FormationController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    // ! Afficher les formations et les formateurs (endpoint 1)
    /*
     * Proposer l'affichage de toute les formations ainsi
     * que les formateurs.
     * Permettre l'affichage des formateurs affiliés à la formation
     * sélectionnée.
     * (A voir pour garder la liste générale de formations,
     * pour permettre de sélectionner une autre formation et renvoyer
     * sa liste de formateur)
     */

    /**
     * GetAllCourseWithTeachers ✅
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
     * GetOneCourse ✅
     *
     * @param id
     * @return
     * 
     *         Retourne une formation et sa liste de formateur
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseWithTeachersDTO> getOneCourse(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(courseService.getOneCourse(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // ! Formulaire de création de formation (endpoint 1 (modal))
    /*
     * Proposer un formulaire de création de formation simple
     */

    /**
     * CreateCourse ✅
     *
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO dto) {
        return new ResponseEntity<>(courseService.createCourse(dto), HttpStatus.CREATED);
    }

    /**
     * UpdateCourse ✅
     *
     * @param id
     * @param dto
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer id, @RequestBody CourseDTO dto) {
        return new ResponseEntity<>(courseService.updateCourse(id, dto), HttpStatus.OK);
    }

    /**
     * DeleteCourse ✅
     *
     * @param id
     * @param response
     */
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Integer id, HttpServletResponse response) {
        courseService.removeCourse(id, response);
    }

    // ! Formulaire de création de formateur (endpoint 2)
    /*
     * Proposer un formulaire de création de formateur
     */

    /**
     * GetAllTechers ✅
     *
     * @return
     * 
     *         retourne tout les formateurs
     */
    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    /**
     * CreateTeacher ✅
     *
     * @param dto
     * @return
     */
    @PostMapping("/teachers")
    public ResponseEntity<?> createTeacher(@RequestBody TeacherDTO dto) {
        return new ResponseEntity<>(teacherService.createTeacher(dto), HttpStatus.CREATED);
    }

    /**
     * UpdateTeacher ✅
     *
     * @param id
     * @param dto
     * @return
     */
    @PatchMapping("/teachers/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable UUID id, @RequestBody TeacherDTO dto) {
        return new ResponseEntity<>(teacherService.updateTeacher(id, dto), HttpStatus.OK);
    }

    /**
     * DeleteTeacher ✅
     *
     * @param id
     * @param response
     */
    @DeleteMapping("/teachers/{id}")
    public void deleteTeacher(@PathVariable UUID id, HttpServletResponse response) {
        teacherService.removeTeacher(id, response);
    }

    // ! Formulaire d'affiliation de formateur (endpoint 1 (modal))
    /*
     * Proposer un formulaire permettant d'entrer une formation
     * spécifique et une liste (un / plusieurs) de formateurs
     */

    /**
     * AddTeachersToCourse ✅
     *
     * @param courseId
     * @param teacherIds
     * @return
     * 
     *         Intègre une liste de formateur pour une formation
     */
    @PostMapping("/{courseId}/teachers")
    public ResponseEntity<CourseWithTeachersDTO> addTeachersToCourse(@PathVariable Integer courseId,
            @RequestBody List<UUID> teacherIds) {
        try {
            return new ResponseEntity<>(courseService.addTeacherToCourse(courseId, teacherIds), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * RemoveTeachersFromCourse ✅
     * 
     * @param courseId
     * @param teacherIds
     * @return
     * 
     * Supprime une liste de formateurs d'une formation
     */
    @PatchMapping("/{courseId}/teachers")
    public ResponseEntity<CourseWithTeachersDTO> removeTeachersFromCourse(@PathVariable Integer courseId,
            @RequestBody List<UUID> teacherIds) {
        return new ResponseEntity<>(courseService.removeTeacherFromCourse(courseId, teacherIds), HttpStatus.OK);
    }
}
