package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.repository.SessionRepository;
import br.com.ntconsult.DevBackNTSicredi.repository.VoteRepository;
import br.com.ntconsult.DevBackNTSicredi.service.validator.AssociateIdValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

    @Mock
    private VoteRepository mockVoteRepository;
    @Mock
    private SessionRepository mockSessionRepository;
    @Mock
    private AssociateIdValidator mockAssociateIdValidator;

    @InjectMocks
    private VoteServiceImpl voteServiceImplUnderTest;

    @Test
    void testPostVote() {
        // Setup
        Session session = new Session();
        session.setStart(LocalDateTime.now(ZoneId.of( "Brazil/East" )));
        session.setEnd(LocalDateTime.now(ZoneId.of( "Brazil/East" )).plusDays(1));
        session.setId(0l);
        session.setVotes(new ArrayList<Vote>());

        Vote vote = new Vote();
        vote.setAssociateId("303030303");
        vote.setValue(Vote.Answer.SIM);
        vote.setSession(session);

        // Configure SessionRepository.findById(...).
        when(mockSessionRepository.findById(any(Long.class))).thenReturn(Optional.of(session));

        // Configure VoteRepository.save(...).
        when(mockVoteRepository.save(vote)).thenReturn(vote);

        // Run the test
        Vote result = voteServiceImplUnderTest.postVote(vote);

        // Verify the results
        assertTrue(result != null);
    }

    @Test
    void testPostVote_SessionRepositoryReturnsAbsent() {
        // Setup
        Session session = new Session();
        session.setStart(LocalDateTime.now(ZoneId.of( "Brazil/East" )));
        session.setEnd(LocalDateTime.now(ZoneId.of( "Brazil/East" )).plusDays(1));
        session.setId(0l);
        session.setVotes(new ArrayList<Vote>());

        // Configure SessionRepository.findById(...).
        when(mockSessionRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // Run the test
        Vote result = voteServiceImplUnderTest.postVote(new Vote(0l, Vote.Answer.SIM, "random", session));

        // Verify the results
        assertTrue(result == null);
    }

    @Test
    void testPostVoteWithAssociateIdValidation() {
        // Setup
        Session session = new Session();
        session.setStart(LocalDateTime.now(ZoneId.of( "Brazil/East" )));
        session.setEnd(LocalDateTime.now(ZoneId.of( "Brazil/East" )).plusDays(1));
        session.setId(0l);
        session.setVotes(new ArrayList<Vote>());

        Vote vote = new Vote();
        vote.setAssociateId("303030303");
        vote.setValue(Vote.Answer.SIM);
        vote.setSession(session);

        // Configure SessionRepository.findById(...).
        when(mockSessionRepository.findById(any(Long.class))).thenReturn(Optional.of(session));

//        //Configure associateIdValidator.validateAssociateId
//        when(mockAssociateIdValidator.validateAssociateId("303030303")).thenReturn(true);

        // Configure VoteRepository.save(...).
        when(mockVoteRepository.save(vote)).thenReturn(vote);

        // Run the test
        Vote result = voteServiceImplUnderTest.postVote(vote);

        // Verify the results
        assertTrue(result != null);
    }

    @Test
    void testPostVoteWithAssociateIdValidation_SessionRepositoryReturnsAbsent() {
        // Setup
        final Vote vote = new Vote(0L, Vote.Answer.SIM, "associateId", new Session(0L, new Topic(0L, "value"), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), List.of()));
        when(mockSessionRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Vote result = voteServiceImplUnderTest.postVoteWithAssociateIdValidation(vote);

        // Verify the results
        assertTrue(result == null);
    }
}
