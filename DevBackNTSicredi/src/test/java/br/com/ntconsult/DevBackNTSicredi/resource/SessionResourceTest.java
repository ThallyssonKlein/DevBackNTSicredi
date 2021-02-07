package br.com.ntconsult.DevBackNTSicredi.resource;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.service.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SessionResource.class)
class SessionResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService mockSessionService;

    @Test
    void testPostSession() throws Exception {
        // Setup

        // Configure SessionService.postSession(...).
        final Session session = new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null)));
        when(mockSessionService.postSession(any(Session.class))).thenReturn(session);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/v1/session")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetSessionResults() throws Exception {
        // Setup

        // Configure SessionService.getOneSession(...).
        final Optional<Session> session = Optional.of(new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null))));
        when(mockSessionService.getOneSession(0L)).thenReturn(session);

        when(mockSessionService.findYesCountBySessionId(any(Session.class))).thenReturn(0);
        when(mockSessionService.findNoCountBySessionId(any(Session.class))).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/session/{id}/results", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetSessionResults_ThrowsIOException() throws Exception {
        // Setup

        // Configure SessionService.getOneSession(...).
        final Optional<Session> session = Optional.of(new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null))));
        when(mockSessionService.getOneSession(0L)).thenReturn(session);

        when(mockSessionService.findYesCountBySessionId(any(Session.class))).thenReturn(0);
        when(mockSessionService.findNoCountBySessionId(any(Session.class))).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/session/{id}/results", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetSessionResults_SessionServiceGetOneSessionReturnsAbsent() throws Exception {
        // Setup
        when(mockSessionService.getOneSession(0L)).thenReturn(Optional.empty());
        when(mockSessionService.findYesCountBySessionId(any(Session.class))).thenReturn(0);
        when(mockSessionService.findNoCountBySessionId(any(Session.class))).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/session/{id}/results", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetOneSession() throws Exception {
        // Setup

        // Configure SessionService.getOneSession(...).
        final Optional<Session> session = Optional.of(new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList(new Vote(0L, Vote.Answer.SIM, "associateId", null))));
        when(mockSessionService.getOneSession(0L)).thenReturn(session);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/session/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetOneSession_SessionServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockSessionService.getOneSession(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/session/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
