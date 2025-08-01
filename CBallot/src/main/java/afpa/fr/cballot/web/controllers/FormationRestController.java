package afpa.fr.cballot.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.services.CourseService;
import afpa.fr.cballot.services.TeacherService;

@RestController
@RequestMapping("/api/formations")
public class FormationRestController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public FormationRestController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    
}
