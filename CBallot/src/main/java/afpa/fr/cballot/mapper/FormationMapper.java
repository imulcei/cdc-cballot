package afpa.fr.cballot.mapper;

import afpa.fr.cballot.dto.FormationDTO;
import afpa.fr.cballot.entities.Formation;

public class FormationMapper {
    public FormationDTO converteToDTO(Formation formation) {
        return new FormationDTO(formation);
    }

    public Formation converteToEntity(FormationDTO dto) {
        return new Formation(dto);
    }
}
