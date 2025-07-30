package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Admin;

public class AdminDTO extends PersonDTO {
    private Integer id;
    private String password;

    public AdminDTO() {
    }

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.password = admin.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
