package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Session;

public class SessionDTO {
    private Integer id;
    private String email;

    public SessionDTO() {
    }

    public SessionDTO(Session session) {
        this.id = session.getId();
        this.email = session.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
