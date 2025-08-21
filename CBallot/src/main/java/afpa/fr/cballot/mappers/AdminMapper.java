package afpa.fr.cballot.mappers;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.AdminDTO;
import afpa.fr.cballot.entities.Admin;

@Service
public class AdminMapper {
    public AdminDTO converteToDTO(Admin admin) {
        return new AdminDTO(admin);
    }

    public Admin converteToEntity(AdminDTO dto) {
        return new Admin(dto);
    }
}
