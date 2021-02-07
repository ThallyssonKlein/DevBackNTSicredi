package br.com.ntconsult.DevBackNTSicredi.service;

import br.com.ntconsult.DevBackNTSicredi.entity.Vote;

public interface VoteService {

    Vote postVote(Vote vote);
    Vote postVoteWithAssociateIdValidation(Vote vote);
}
