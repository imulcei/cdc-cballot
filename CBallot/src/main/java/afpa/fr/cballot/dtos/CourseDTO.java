package afpa.fr.cballot.dtos;

import afpa.fr.cballot.entities.Course;

public class CourseDTO {
    private Integer id;
    private String libelle;

    public CourseDTO() {
    }

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.libelle = course.getLibelle();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
