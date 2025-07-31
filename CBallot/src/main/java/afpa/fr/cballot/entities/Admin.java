package afpa.fr.cballot.entities;

import afpa.fr.cballot.dto.AdminDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends Person {

    @Column(name = "password", nullable = false)
    private String password;

    public Admin() {
    }

    public Admin(AdminDTO dto) {
        super(dto);
        this.password = dto.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
