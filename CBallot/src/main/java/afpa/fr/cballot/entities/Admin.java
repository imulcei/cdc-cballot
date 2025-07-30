package afpa.fr.cballot.entities;

import afpa.fr.cballot.dto.AdminDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Admin extends Person {
    @Column(name = "password", nullable = false)
    private String password;

    public Admin() {
    }

    public Admin(AdminDTO dto) {
        this.password = dto.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
