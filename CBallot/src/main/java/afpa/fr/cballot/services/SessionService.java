package afpa.fr.cballot.services;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.repositories.SessionRepository;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
