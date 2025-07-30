package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Teacher;

public class TeacherDTO extends PersonDTO {
    private Integer id;
    private String password;

    public TeacherDTO() {
    }

    public TeacherDTO(String password) {
        this.password = password;
    }

    public TeacherDTO(Teacher teacher) {
        super(teacher);
        this.id = teacher.getId();
        this.password = teacher.getPassword();
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
