package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.mapper.SessionMapper;
import afpa.fr.cballot.repositories.SessionRepository;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final SessionMapper mapper;

    public SessionService(SessionRepository sessionRepository, SessionMapper mapper) {
        this.sessionRepository = sessionRepository;
        this.mapper = mapper;
    }
}
