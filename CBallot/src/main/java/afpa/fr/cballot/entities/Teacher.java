package afpa.fr.cballot.entities;

import afpa.fr.cballot.dto.TeacherDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Teacher extends Person {
    @Column(name = "password", nullable = false)
    private String password;

    public Teacher() {
    }

    public Teacher(TeacherDTO dto) {
        this.password = dto.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
