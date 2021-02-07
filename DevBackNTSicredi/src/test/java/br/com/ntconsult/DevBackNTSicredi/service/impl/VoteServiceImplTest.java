package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

    @Mock
    private VoteRepository mockVoteRepository;

    @InjectMocks
    private VoteServiceImpl voteServiceImplUnderTest;

    @Test
    void testPostVote() {
        // Setup
        final Vote vote = new Vote(0L, Vote.Answer.SIM, "associateId", new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList()));

        // Configure VoteRepository.save(...).
        final Vote vote1 = new Vote(0L, Vote.Answer.SIM, "associateId", new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList()));
        when(mockVoteRepository.save(any(Vote.class))).thenReturn(vote1);

        // Run the test
        final Vote result = voteServiceImplUnderTest.postVote(vote);

        // Verify the results
    }

    @Test
    void testPostVoteWithAssociateIdValidation() {
        // Setup
        final Vote vote = new Vote(0L, Vote.Answer.SIM, "associateId", new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList()));

        // Configure VoteRepository.save(...).
        final Vote vote1 = new Vote(0L, Vote.Answer.SIM, "associateId", new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList()));
        when(mockVoteRepository.save(any(Vote.class))).thenReturn(vote1);

        // Run the test
        final Vote result = voteServiceImplUnderTest.postVoteWithAssociateIdValidation(vote);

        // Verify the results
    }
}
