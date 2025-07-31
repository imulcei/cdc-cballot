package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Session;

public class SessionDTO {
    private Integer id;
    private String name;

    public SessionDTO() {
    }

    public SessionDTO(Session session) {
        this.id = session.getId();
        this.name = session.getName();
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
}
