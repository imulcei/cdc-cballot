package afpa.fr.cballot.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.dtos.SessionDTO;
import afpa.fr.cballot.dtos.SessionWithAllStudentsDTO;
import afpa.fr.cballot.dtos.SessionWithStudentsDTO;
import afpa.fr.cballot.dtos.StudentDTO;
import afpa.fr.cballot.services.SessionService;
import afpa.fr.cballot.services.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final StudentService studentService;
    private final SessionService sessionService;

    public SessionController(StudentService studentService, SessionService sessionService) {
        this.sessionService = sessionService;
        this.studentService = studentService;
    }

    // ! Formulaire de création de session (endpoint 1)
    /*
     * proposer un formulaire de création de session
     * et afficher la liste de stagiaire (générale)
     */

    /**
     * presentAllStudents ✅
     *
     * @return
     * 
     *         transmet la liste de stagiaire pour permettre de choisir les
     *         stagiaires, pour
     *         la session
     */
    @GetMapping
    public ResponseEntity<List<StudentDTO>> presentAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    /**
     * CreateSessionWithStudents ✅
     *
     * @param dto
     * @return
     * 
     *         Crée une session avec une liste de stagiaire
     */
    @PostMapping
    public ResponseEntity<SessionWithStudentsDTO> createSessionWithStudents(@RequestBody SessionWithStudentsDTO dto) {
        return new ResponseEntity<>(sessionService.createSession(dto), HttpStatus.CREATED);
    }

    // ! Afficher la session (endpoint 1)
    /*
     * Présenter la session, sa liste de stagiaire
     * et afficher la liste de ses stagiaires
     */

    @GetMapping("/all")
    public ResponseEntity<List<SessionDTO>> getAllSessions() {
        return new ResponseEntity<>(sessionService.getSessions(), HttpStatus.OK);
    }

    /**
     * GetSession ✅
     *
     * @param id
     * @return
     * 
     *         renvoie une session
     */
    @GetMapping("/{id}")
    public ResponseEntity<SessionWithStudentsDTO> getSession(@PathVariable Integer id) {
        return new ResponseEntity<>(sessionService.getOneSession(id), HttpStatus.OK);
    }

    // ! Modifier la session (endpoint 1)
    /*
     * Permettre la modification de la session
     */

    /**
     * UpdateSession ✅
     *
     * @param id
     * @param dto
     * @return
     * 
     *         Permet la mise à jour la session
     */
    @PatchMapping("/{id}")
    public ResponseEntity<SessionDTO> updateSession(@PathVariable Integer id, @RequestBody SessionWithStudentsDTO dto) {
        try {
            return new ResponseEntity<>(sessionService.updateSession(id, dto), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // ! Ajouter un stagiaire à la session (endpoint 2)
    /*
     * Afficher la liste des stagiaires liée à la session
     * et proposer la liste générales des stagiaires
     * pour permettre l'ajout de nouveau stagiaire
     * dans la session
     */

    /**
     * PresentStudents ✅
     *
     * @param id
     * @return
     * 
     *         Retourne la session, sa liste de stagiaire et la liste générale
     *         des stagiaires
     */
    @GetMapping("/{id}/students")
    public ResponseEntity<SessionWithAllStudentsDTO> presentStudents(@PathVariable Integer id) {
        return new ResponseEntity<>(sessionService.getOneSessionWithAllStudents(id), HttpStatus.OK);
    }

    /**
     * AddStudents ✅
     *
     * @param id
     * @param studentsIds
     * @return
     * 
     *         Ajoute une liste de stagiaire à la session
     */
    @PostMapping("/{id}/students")
    public ResponseEntity<SessionWithAllStudentsDTO> addStudents(@PathVariable Integer id, @RequestBody List<UUID> studentsIds) {
        try {
            sessionService.addStudentsToSession(id, studentsIds);
            return new ResponseEntity<>(sessionService.getOneSessionWithAllStudents(id), HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // ! Retirer un stagiaire à la session (endpoint 2)
    /*
     * Afficher la liste des stagiaires lié à la session
     * et proposer la liste générales des stagiaires
     * pour permettre le retrait de stagiaire
     * de la session
     */

    /**
     * RemoveStudentsFromSession ✅
     *
     * @param id
     * @param studentsIds
     * @return
     * 
     *         Supprime une liste de stagiaire à la session
     */
    @PatchMapping("/{id}/students")
    public ResponseEntity<SessionWithAllStudentsDTO> removeStudentFromSession(@PathVariable Integer id, @RequestBody List<UUID> studentsIds) {
        try {
            sessionService.removeStudentsFromSession(id, studentsIds);
            return new ResponseEntity<>(sessionService.getOneSessionWithAllStudents(id), HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // ! Supprimer la session (endpoint 1)
    /*
     * Dans la suppression de la session, retirer
     * la liste de stagiaire connecté à celle-ci
     */

    /**
     * RemoveSession ✅
     *
     * @param id
     * @param response
     * 
     *                 supprime la session
     */
    @DeleteMapping("/{id}")
    public void removeSession(@PathVariable Integer id, HttpServletResponse response) {
        sessionService.removeSession(id, response);
    }

    // ! Création de stagiaire
    /*
     * Proposer la creation / modification
     * / suppression de stagiaire
     */

    /**
     * GetOneStudent ✅
     *
     * @param id
     * @return
     */
    @GetMapping("/students/{id}")
    public ResponseEntity<?> getOneStudent(@PathVariable UUID id) {
        return new ResponseEntity<>(studentService.getOneStudent(id), HttpStatus.OK);
    }

    /**
     * CreateStudent ✅
     * @param student
     * @return
     */
    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    /**
     * UpdateStudent ✅
     *
     * @param id
     * @param student
     * @return
     */
    @PatchMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable UUID id, @RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
    }

    /**
     * DeleteStudent ✅
     *
     * @param id
     * @param response
     */
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable UUID id, HttpServletResponse response) {
        studentService.removeStudent(id, response);
    }

}
