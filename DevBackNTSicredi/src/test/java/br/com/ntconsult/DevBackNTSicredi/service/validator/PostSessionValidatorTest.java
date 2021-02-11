package br.com.ntconsult.DevBackNTSicredi.service.validator;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostSessionValidatorTest {

    private PostSessionValidator postSessionValidatorUnderTest;

    @BeforeEach
    void setUp() {
        postSessionValidatorUnderTest = new PostSessionValidator();
    }

    @Test
    void testValidateIfSessionEndDateIsBeforeThanNow() {
        // Setup
        Session session = new Session();
        session.setEnd(LocalDateTime.now(ZoneId.of( "Brazil/East" )).minusDays(1));
        // Run the test
        final boolean result = postSessionValidatorUnderTest.validateIfSessionEndDateIsBeforeThanNow(session);

        // Verify the results
        assertThat(result).isTrue();
    }
}
