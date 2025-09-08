package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.AdminDTO;
import afpa.fr.cballot.entities.Admin;
import afpa.fr.cballot.mappers.AdminMapper;
import afpa.fr.cballot.repositories.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    /**
     * GetAllAdmins (méthode à ne pas forcément à utiliser)
     * 
     * @return
     *
     *         Renvoie la liste d'Admin
     */
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(admin -> new AdminDTO(admin))
                .collect(Collectors.toList());
    }

    /**
     * GetOneAdmin
     * 
     * @param id
     * @return
     * 
     *         Retourne un admin selon ID
     */
    public AdminDTO getOneAdmin(UUID id) {
        return adminMapper.converteToDTO(adminRepository.findById(id).orElse(null));
    }

    /**
     * CreateAdmin
     * 
     * @param dto
     * @return
     * 
     *         Enregistre un admin selon les params entrés
     */
    public AdminDTO createAdmin(AdminDTO dto) {
        return adminMapper.converteToDTO(adminRepository.save(adminMapper.converteToEntity(dto)));
    }

    /**
     * UpdateAdmin
     * 
     * @param id
     * @param dto
     * @return
     * 
     *         Permet la modification du "password" de l'admin
     */
    public AdminDTO updateAdmin(UUID id, AdminDTO dto) {
        Optional<Admin> originalAdmin = adminRepository.findById(id);

        if (originalAdmin.isEmpty()) {
            throw new EntityNotFoundException("Admin not found");
        }

        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("Id mismatch between path and body");
        }

        Admin admin = originalAdmin.get();
        admin.setPassword(dto.getPassword());
        admin.setLastname(dto.getLastname());
        admin.setFirstname(dto.getFirstname());

        return adminMapper.converteToDTO(adminRepository.save(admin));
    }

    /**
     * RemoveAdmin
     * 
     * @param id
     * @param response
     */
    public void removeAdmin(UUID id, HttpServletResponse response) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
