package afpa.fr.cballot.dto;

import afpa.fr.cballot.entities.Formation;

public class FormationDTO {
    private Integer id;
    private String name;

    public FormationDTO() {
    }

    public FormationDTO(Formation formation) {
        this.id = formation.getId();
        this.name = formation.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
