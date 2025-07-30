package afpa.fr.cballot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Admin extends Person {
    @Column(name = "password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
