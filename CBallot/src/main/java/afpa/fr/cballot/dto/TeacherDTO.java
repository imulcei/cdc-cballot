package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Teacher;

public class TeacherDTO extends PersonDTO {
    private String password;

    public TeacherDTO() {
    }

    public TeacherDTO(String password) {
        this.password = password;
    }

    public TeacherDTO(Teacher teacher) {
        this.password = teacher.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
