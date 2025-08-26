package afpa.fr.cballot.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.entities.Teacher;
import afpa.fr.cballot.entities.entityuserdetails.TeacherUserDetails;
import afpa.fr.cballot.repositories.TeacherRepository;

@Service
public class TeacherUserDetailsServiceImpl implements UserDetailsService {

    private final TeacherRepository teacherRepository;

    public TeacherUserDetailsServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository
                                        .findByEmail(username)
                                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " +username));

        return new TeacherUserDetails(teacher);
    }
}
