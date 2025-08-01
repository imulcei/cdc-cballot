package afpa.fr.cballot.dtos;

import afpa.fr.cballot.entities.Course;

public class CourseDTO {
    private Integer id;
    private String name;

    public CourseDTO() {
    }

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
