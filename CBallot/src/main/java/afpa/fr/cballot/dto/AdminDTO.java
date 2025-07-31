package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Admin;

public class AdminDTO extends PersonDTO {
    private String password;

    public AdminDTO() {
    }

    public AdminDTO(Admin admin) {
        super(admin);
        this.password = admin.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
