package afpa.fr.cballot.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.dtos.SessionDTO;
import afpa.fr.cballot.services.CourseService;
import afpa.fr.cballot.services.SessionService;
import afpa.fr.cballot.services.StudentService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/sessions")
public class SessionRestController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final SessionService sessionService;

    public SessionRestController(CourseService courseService, StudentService studentService, SessionService sessionService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.sessionService = sessionService;
    }

    /**
     * GetAllSessions
     * 
     * @return
     * 
     * Retourne toute les sessions (générales)
     *
     */
    @GetMapping
    public ResponseEntity<?> getAllSessions() {
        return new ResponseEntity<>(sessionService.getAllSessions(), HttpStatus.OK);
    }

    /**
     * GetSessionToCourse
     *
     * @param id
     * @return
     * 
     * Retourne la liste des sessions d'une formation
     */
    @GetMapping("/course/{id}")
    public ResponseEntity<?> getSessionsToCourse(@PathVariable Integer id) {
        return new ResponseEntity<>(sessionService.getSessionsToThisCourse(id), HttpStatus.OK);
    }



}
