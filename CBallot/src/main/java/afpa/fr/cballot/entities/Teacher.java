package afpa.fr.cballot.entities;

import java.util.ArrayList;
import java.util.List;

import afpa.fr.cballot.dtos.TeacherDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "teacher_course",
                    joinColumns = @JoinColumn(name = "id_teacher"),
                    inverseJoinColumns = @JoinColumn(name = "id_course"))
    List<Course> courses = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(TeacherDTO dto) {
        super(dto);
        this.password = dto.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
