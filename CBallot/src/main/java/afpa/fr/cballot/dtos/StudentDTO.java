package afpa.fr.cballot.dtos;

import afpa.fr.cballot.entities.Student;

public class StudentDTO extends PersonDTO {

    private PairDto pairDto;
    private SessionDTO sessionDto;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        super(student);
    }

    public PairDto getPairDto() {
        return pairDto;
    }

    public void setPairDto(PairDto pairDto) {
        this.pairDto = pairDto;
    }

    public SessionDTO getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDTO sessionDto) {
        this.sessionDto = sessionDto;
    }


}
