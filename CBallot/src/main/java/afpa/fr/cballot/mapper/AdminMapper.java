package afpa.fr.cballot.mapper;

import afpa.fr.cballot.dto.AdminDTO;
import afpa.fr.cballot.entities.Admin;

public class AdminMapper {
    public AdminDTO converteToDTO(Admin admin) {
        return new AdminDTO(admin);
    }

    public Admin converteToEntity(AdminDTO dto) {
        return new Admin(dto);
    }
}
