package afpa.fr.cballot.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import afpa.fr.cballot.entities.Student;

public class PairDto {
    private Integer id;
    private Integer counter;
    private Integer id_election;
    @JsonIgnore
    private ElectionDto election;
    private List<Student> students;

    public PairDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public ElectionDto getElection() {
        return election;
    }

    public void setElection(ElectionDto election) {
        this.election = election;
    }

    public Integer getId_election() {
        return id_election;
    }

    public void setId_election(Integer id_election) {
        this.id_election = id_election;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
