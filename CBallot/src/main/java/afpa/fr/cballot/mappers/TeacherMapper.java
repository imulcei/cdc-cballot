package afpa.fr.cballot.mappers;

import afpa.fr.cballot.dtos.TeacherDTO;
import afpa.fr.cballot.entities.Teacher;

public class TeacherMapper {
    public TeacherDTO converteToDTO(Teacher teacher) {
        return new TeacherDTO(teacher);
    }

    public Teacher converteToEntity(TeacherDTO dto) {
        return new Teacher(dto);
    }
}
