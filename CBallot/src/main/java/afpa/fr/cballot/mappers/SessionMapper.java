package afpa.fr.cballot.mappers;

import afpa.fr.cballot.dtos.SessionDTO;
import afpa.fr.cballot.entities.Session;

public class SessionMapper {
    public SessionDTO converteToDTO(Session session) {
        return new SessionDTO(session);
    }

    public Session converteToEntity(SessionDTO dto) {
        return new Session(dto);
    }
}
