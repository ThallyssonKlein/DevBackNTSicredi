package br.com.ntconsult.DevBackNTSicredi.service;

import br.com.ntconsult.DevBackNTSicredi.entity.Topic;

import java.util.Optional;

public interface TopicService {

    public Topic postTopic(Topic topic);

    public Optional<Topic> getOneTopic(long id);
}
