package br.com.ntconsult.DevBackNTSicredi.service;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    Session postSession(Session session);
    int findYesCountBySessionId(Session sessionId);
    int findNoCountBySessionId(Session sessionId);
    Optional<Session> getOneSession(long id);
    List<Session> findAllSessions();
    Session findSessionByTopic(Topic topic);
}
