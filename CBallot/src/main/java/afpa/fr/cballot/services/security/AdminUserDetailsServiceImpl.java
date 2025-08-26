package afpa.fr.cballot.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.entities.Admin;
import afpa.fr.cballot.entities.entityuserdetails.AdminUserDetails;
import afpa.fr.cballot.repositories.AdminRepository;

@Service
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminUserDetailsServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository
                            .findByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));

        return new AdminUserDetails(admin);
    }
}
