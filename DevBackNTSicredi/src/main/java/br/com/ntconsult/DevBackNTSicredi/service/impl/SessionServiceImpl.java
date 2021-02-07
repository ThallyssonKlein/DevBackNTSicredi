package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.repository.SessionRepository;
import br.com.ntconsult.DevBackNTSicredi.repository.VoteRepository;
import br.com.ntconsult.DevBackNTSicredi.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Session postSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public int findYesCountBySessionId(Session sessionId) {
        return voteRepository.findYesCountBySessionId(sessionId);
    }

    @Override
    public int findNoCountBySessionId(Session sessionId) {
        return voteRepository.findNoCountBySessionId(sessionId);
    }

    @Override
    public Optional<Session> getOneSession(long id) {
        return sessionRepository.findById(id);
    }
}
