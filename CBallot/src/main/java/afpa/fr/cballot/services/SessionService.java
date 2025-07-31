package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dto.SessionDTO;
import afpa.fr.cballot.entities.Session;
import afpa.fr.cballot.mapper.SessionMapper;
import afpa.fr.cballot.repositories.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final SessionMapper mapper;

    public SessionService(SessionRepository sessionRepository, SessionMapper mapper) {
        this.sessionRepository = sessionRepository;
        this.mapper = mapper;
    }

    /**
     * GetAllSessions
     * @return
     * 
     * Retourne une liste de sessions
     */
    public List<SessionDTO> getAllSessions() {
        return sessionRepository.findAll()
                                .stream()
                                .map(session -> new SessionDTO(session))
                                .collect(Collectors.toList());
    }

    /**
     * GetOneSession
     * @param id
     * @return
     * 
     * Retourne une session
     */
    public SessionDTO getOneSession(Integer id) {
        return mapper.converteToDTO(sessionRepository.findById(id).orElse(null));
    }

    /**
     * CreateSession
     * @param dto
     * @return
     */
    public SessionDTO createSession(SessionDTO dto) {
        Session session = mapper.converteToEntity(dto);
        session = sessionRepository.save(session);
        dto.setId(session.getId());

        return dto;
    }

    /**
     * UpdateSession
     * @param id
     * @param dto
     * @return
     */
    public SessionDTO updateSession(Integer id, SessionDTO dto) {
        Optional<Session> original = sessionRepository.findById(id);

        if (original.isEmpty()){
            throw new EntityNotFoundException("Session not found.");
        }
        if (!id.equals(dto.getId())) {
            throw new IllegalArgumentException("Id mismatch between path and body.");
        }

        Session session = original.get();
        session.setName(dto.getName());

        return mapper.converteToDTO(sessionRepository.save(session));
    }

    /**
     * RemoveSession
     * @param id
     * @param response
     */
    public void removeSession(Integer id, HttpServletResponse response) {
        if (sessionRepository.existsById(id)) {
            sessionRepository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
