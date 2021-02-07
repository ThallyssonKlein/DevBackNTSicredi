package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.repository.SessionRepository;
import br.com.ntconsult.DevBackNTSicredi.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @Mock
    private SessionRepository mockSessionRepository;
    @Mock
    private VoteRepository mockVoteRepository;

    @InjectMocks
    private SessionServiceImpl sessionServiceImplUnderTest;

    @Test
    void testPostSession() {
        // Setup
        final Session session = new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null)));

        // Configure SessionRepository.save(...).
        final Session session1 = new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null)));
        when(mockSessionRepository.save(any(Session.class))).thenReturn(session1);

        // Run the test
        final Session result = sessionServiceImplUnderTest.postSession(session);

        // Verify the results
    }

    @Test
    void testFindYesCountBySessionId() {
        // Setup
        final Session sessionId = new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null)));
        when(mockVoteRepository.findYesCountBySessionId(any(Session.class))).thenReturn(0);

        // Run the test
        final int result = sessionServiceImplUnderTest.findYesCountBySessionId(sessionId);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testFindNoCountBySessionId() {
        // Setup
        final Session sessionId = new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null)));
        when(mockVoteRepository.findNoCountBySessionId(any(Session.class))).thenReturn(0);

        // Run the test
        final int result = sessionServiceImplUnderTest.findNoCountBySessionId(sessionId);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testGetOneSession() {
        // Setup

        // Configure SessionRepository.findById(...).
        final Optional<Session> session = Optional.of(new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null))));
        when(mockSessionRepository.findById(0L)).thenReturn(session);

        // Run the test
        final Optional<Session> result = sessionServiceImplUnderTest.getOneSession(0L);

        // Verify the results
    }

    @Test
    void testGetOneSession_SessionRepositoryReturnsAbsent() {
        // Setup
        when(mockSessionRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Session> result = sessionServiceImplUnderTest.getOneSession(0L);

        // Verify the results
    }

    @Test
    void testFindAllSessions() {
        // Setup

        // Configure SessionRepository.findAll(...).
        final List<Session> sessions = Arrays.asList(new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null))));
        when(mockSessionRepository.findAll()).thenReturn(sessions);

        // Run the test
        final List<Session> result = sessionServiceImplUnderTest.findAllSessions();

        // Verify the results
    }

    @Test
    void testFindAllSessions_SessionRepositoryReturnsNoItems() {
        // Setup
        when(mockSessionRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Session> result = sessionServiceImplUnderTest.findAllSessions();

        // Verify the results
    }
}
