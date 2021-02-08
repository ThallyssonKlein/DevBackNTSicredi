package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.repository.SessionRepository;
import br.com.ntconsult.DevBackNTSicredi.repository.TopicRepository;
import br.com.ntconsult.DevBackNTSicredi.repository.VoteRepository;
import br.com.ntconsult.DevBackNTSicredi.service.SessionService;
import br.com.ntconsult.DevBackNTSicredi.service.validator.PostSessionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private TopicRepository topicRepository;
    private PostSessionValidator postSessionValidator = new PostSessionValidator();

    @Override
    public Session postSession(Session session) {
        if(session.getEnd() != null && postSessionValidator.validateIfSessionEndDateIsBeforeThanNow(session)){
            System.out.println("Entrou no 1");
            return null;
        }
        Session sessionWithTheSameTopic = findSessionByTopic(session.getTopic());
        if(sessionWithTheSameTopic != null){
            System.out.println("Entrou no 2");
            return null;
        }
        if(!topicRepository.existsById(session.getTopic().getId())){
            System.out.println("Entrou no 3");
            return null;
        }
        if(session.getVotes() != null){
            for(Vote vote : session.getVotes()){
                if(!voteRepository.existsById(vote.getId())){
                    System.out.println("Entrou no 4");
                    return null;
                }
            }
        }
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

    @Override
    public List<Session> findAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findSessionByTopic(Topic topic) {
        return sessionRepository.fidSessionByTopic(topic);
    }
}
