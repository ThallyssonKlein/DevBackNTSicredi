package br.com.ntconsult.DevBackNTSicredi.service.impl;

import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.repository.TopicRepository;
import br.com.ntconsult.DevBackNTSicredi.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic postTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Optional<Topic> getOneTopic(long id) {
        return topicRepository.findById(id);
    }
}
