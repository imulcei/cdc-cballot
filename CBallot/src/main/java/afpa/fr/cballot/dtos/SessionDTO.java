package afpa.fr.cballot.dtos;

import java.util.Date;

import afpa.fr.cballot.entities.Session;

public class SessionDTO {
    private Integer id;
    private String name;
    private Date start_date;
    private Date end_date;

    public SessionDTO() {
    }

    public SessionDTO(Session session) {
        this.id = session.getId();
        this.name = session.getName();
        this.start_date = session.getStart_date();
        this.end_date = session.getEnd_date();
    }

    public SessionDTO(String name, Date start_date, Date end_date) {
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
}
