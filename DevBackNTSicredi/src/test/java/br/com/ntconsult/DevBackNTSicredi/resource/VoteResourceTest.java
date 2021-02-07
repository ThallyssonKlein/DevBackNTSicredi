package br.com.ntconsult.DevBackNTSicredi.resource;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.service.VoteService;
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

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VoteResource.class)
class VoteResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService mockVoteService;

    @Test
    void testPostVote() throws Exception {
        // Setup

        // Configure VoteService.postVote(...).
        final Vote vote = new Vote(0L, Vote.Answer.SIM, "associateId", new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList()));
        when(mockVoteService.postVote(any(Vote.class))).thenReturn(vote);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/v1/vote")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testPostVoteWithAssociateIdValidation() throws Exception {
        // Setup

        // Configure VoteService.postVoteWithAssociateIdValidation(...).
        final Vote vote = new Vote(0L, Vote.Answer.SIM, "associateId", new Session(0L, new Topic(0L, "value"), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), Arrays.asList()));
        when(mockVoteService.postVoteWithAssociateIdValidation(any(Vote.class))).thenReturn(vote);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/v2/vote")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
