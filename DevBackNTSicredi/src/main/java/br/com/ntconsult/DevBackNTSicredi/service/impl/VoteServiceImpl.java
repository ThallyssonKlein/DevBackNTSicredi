package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.repository.SessionRepository;
import br.com.ntconsult.DevBackNTSicredi.repository.VoteRepository;
import br.com.ntconsult.DevBackNTSicredi.service.VoteService;
import br.com.ntconsult.DevBackNTSicredi.service.validator.AssociateIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private SessionRepository sessionRepository;
    private AssociateIdValidator associateIdValidator = new AssociateIdValidator();

    @Override
    public Vote postVote(Vote vote) {
        Optional<Session> session = sessionRepository.findById(vote.getSession().getId());
        if(!session.isPresent() || LocalDateTime.now(ZoneId.of( "Brazil/East" )).isAfter(session.get().getEnd())) {
            return null;
        }
        for(Vote v : session.get().getVotes()){
            if(v.getAssociateId().equals(vote.getAssociateId())){
                return null;
            }
        }
        return voteRepository.save(vote);
    }

    @Override
    public Vote postVoteWithAssociateIdValidation(Vote vote) {
        Optional<Session> session = sessionRepository.findById(vote.getSession().getId());
        if(!session.isPresent() || LocalDateTime.now(ZoneId.of( "Brazil/East" )).isAfter(session.get().getEnd())) {
            return null;
        }

        if(!associateIdValidator.validateAssociateId(vote.getAssociateId())){
            return null;
        }

        return voteRepository.save(vote);
    }
}
