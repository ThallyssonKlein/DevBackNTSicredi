package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.repository.VoteRepository;
import br.com.ntconsult.DevBackNTSicredi.service.VoteService;
import br.com.ntconsult.DevBackNTSicredi.service.validator.AssociateIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;
    private AssociateIdValidator associateIdValidator = new AssociateIdValidator();

    @Override
    public Vote postVote(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public Vote postVoteWithAssociateIdValidation(Vote vote) {
        if(associateIdValidator.validateAssociateId(vote.getAssociateId())){
            return voteRepository.save(vote);
        }else {
            return null;
        }
    }
}
