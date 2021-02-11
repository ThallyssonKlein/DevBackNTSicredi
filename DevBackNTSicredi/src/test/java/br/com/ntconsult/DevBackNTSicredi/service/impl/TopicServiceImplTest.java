package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {

    @Mock
    private TopicRepository mockTopicRepository;

    @InjectMocks
    private TopicServiceImpl topicServiceImplUnderTest;

    @Test
    void testPostTopic() {
        // Setup
        final Topic topic = new Topic(0L, "value");
        when(mockTopicRepository.save(any(Topic.class))).thenReturn(new Topic(0L, "value"));

        // Run the test
        final Topic result = topicServiceImplUnderTest.postTopic(topic);

        // Verify the results
        assertTrue(result != null);
    }
}
