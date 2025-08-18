package afpa.fr.cballot.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import afpa.fr.cballot.dtos.SessionDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name ="start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_course", referencedColumnName = "id")
    private Course course;

    @JsonIgnore
    @OneToMany(targetEntity = Student.class, mappedBy = "student")
    private List<Student> students;

    public Session() {
    }

    public Session(SessionDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.start_date = dto.getStart_date();
        this.end_date = dto.getEnd_date();
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

    public void setName(String email) {
        this.name = email;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
