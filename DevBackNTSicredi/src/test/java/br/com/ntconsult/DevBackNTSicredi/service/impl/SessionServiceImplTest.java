package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.repository.SessionRepository;
import br.com.ntconsult.DevBackNTSicredi.repository.TopicRepository;
import br.com.ntconsult.DevBackNTSicredi.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @Mock
    private SessionRepository mockSessionRepository;
    @Mock
    private VoteRepository mockVoteRepository;
    @Mock
    private TopicRepository mockTopicRepository;

    @InjectMocks
    private SessionServiceImpl sessionServiceImplUnderTest;

    @Test
    void testPostSession() {
        // Setup
        Session session = new Session();
        Topic topic = new Topic();
        topic.setValue("Random value");
        session.setTopic(topic);

        // Configure sessionServiceImplUnderTest.fidSessionByTopic(...).
        when(sessionServiceImplUnderTest.findSessionByTopic(session.getTopic())).thenReturn(null);
        when(mockTopicRepository.existsById(0l)).thenReturn(true);

        //Configure sessionRepository.save
        when(mockSessionRepository.save(session)).thenReturn(new Session(0l, topic, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), null));

        // Run the test
        Session result = sessionServiceImplUnderTest.postSession(session);

        // Verify the results
        assertTrue(result != null);
    }

    @Test
    void testFindYesCountBySessionId() {
        // Setup
        final Session sessionId = new Session(0L, new Topic(0L, "value"), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), List.of(new Vote(0L, Vote.Answer.SIM, "associateId", null)));
        when(mockVoteRepository.findYesCountBySessionId(any(Session.class))).thenReturn(0);

        // Run the test
        final int result = sessionServiceImplUnderTest.findYesCountBySessionId(sessionId);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testFindNoCountBySessionId() {
        // Setup
        final Session sessionId = new Session(0L, new Topic(0L, "value"), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), List.of(new Vote(0L, Vote.Answer.SIM, "associateId", null)));
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
        final Optional<Session> session = Optional.of(new Session(0L, new Topic(0L, "value"), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), List.of(new Vote(0L, Vote.Answer.SIM, "associateId", null))));
        when(mockSessionRepository.findById(0L)).thenReturn(session);

        // Run the test
        final Optional<Session> result = sessionServiceImplUnderTest.getOneSession(0L);

        // Verify the results
        assertTrue(result.isPresent());
    }

    @Test
    void testGetOneSession_SessionRepositoryReturnsAbsent() {
        // Setup
        when(mockSessionRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Session> result = sessionServiceImplUnderTest.getOneSession(0L);

        // Verify the results
        assertTrue(!result.isPresent());
    }

    @Test
    void testFindAllSessions() {
        // Setup

        // Configure SessionRepository.findAll(...).
        final List<Session> sessions = List.of(new Session(0L, new Topic(0L, "value"), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), List.of(new Vote(0L, Vote.Answer.SIM, "associateId", null))));
        when(mockSessionRepository.findAll()).thenReturn(sessions);

        // Run the test
        final List<Session> result = sessionServiceImplUnderTest.findAllSessions();

        // Verify the results
        assertTrue(!result.isEmpty());
    }

    @Test
    void testFindAllSessions_SessionRepositoryReturnsNoItems() {
        // Setup
        when(mockSessionRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Session> result = sessionServiceImplUnderTest.findAllSessions();

        // Verify the results
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindSessionByTopic() {
        // Setup
        final Topic topic = new Topic(0L, "value");

        // Configure SessionRepository.fidSessionByTopic(...).
        final Session session = new Session(0L, new Topic(0L, "value"), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), List.of(new Vote(0L, Vote.Answer.SIM, "associateId", null)));
        when(mockSessionRepository.fidSessionByTopic(any(Topic.class))).thenReturn(session);

        // Run the test
        final Session result = sessionServiceImplUnderTest.findSessionByTopic(topic);

        //Verify the results
        assertTrue(result != null);
    }
}