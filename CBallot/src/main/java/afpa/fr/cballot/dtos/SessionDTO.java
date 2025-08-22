package afpa.fr.cballot.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import afpa.fr.cballot.entities.Session;

public class SessionDTO {
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end_date;
    private Integer id_course;

    public SessionDTO() {
    }

    public SessionDTO(Session session) {
        this.id = session.getId();
        this.name = session.getName();
        this.start_date = session.getStart_date();
        this.end_date = session.getEnd_date();
        this.id_course = session.getCourse().getId();
    }

    public SessionDTO(String name, Date start_date, Date end_date, Integer id_course) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.id_course = id_course;
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

    public Integer getId_course() {
        return id_course;
    }

    public void setId_course(Integer id_course) {
        this.id_course = id_course;
    }
}
