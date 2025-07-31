package afpa.fr.cballot.dto;

import java.util.List;

public class TeacherCourseDTO {
    private List<CourseDTO> courses;
    private List<TeacherDTO> teachers;

    public TeacherCourseDTO() {
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDTO> teachers) {
        this.teachers = teachers;
    }
}
