package afpa.fr.cballot.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import afpa.fr.cballot.dtos.CourseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "libelle")
    private String libelle;

    @ManyToMany
    @JoinTable(name = "teacher_course", joinColumns = @JoinColumn(name = "id_course"), inverseJoinColumns = @JoinColumn(name = "id_teacher"))
    private List<Teacher> teachers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(targetEntity = Session.class, mappedBy = "course")
    private List<Session> sessions;

<<<<<<< HEAD
    public Course() {}
=======
    public Course() {

    }
>>>>>>> 1dcd351f69101618e7b3a272fc435d3a91e4353e

    public Course(CourseDTO dto) {
        this.id = dto.getId();
        this.libelle = dto.getLibelle();
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

    public void setName(String libelle) {
        this.libelle = libelle;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

}
