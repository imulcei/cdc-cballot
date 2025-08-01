package afpa.fr.cballot.mappers;

import afpa.fr.cballot.dtos.AdminDTO;
import afpa.fr.cballot.entities.Admin;

public class AdminMapper {
    public AdminDTO converteToDTO(Admin admin) {
        return new AdminDTO(admin);
    }

    public Admin converteToEntity(AdminDTO dto) {
        return new Admin(dto);
    }
}
