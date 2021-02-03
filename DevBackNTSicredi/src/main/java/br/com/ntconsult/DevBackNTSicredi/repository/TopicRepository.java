package br.com.ntconsult.DevBackNTSicredi.repository;

import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
