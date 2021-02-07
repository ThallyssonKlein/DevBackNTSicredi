package br.com.ntconsult.DevBackNTSicredi.service.scheduled;

import br.com.ntconsult.DevBackNTSicredi.DevBackNtSicrediApplication;
import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.service.SessionService;
import br.com.ntconsult.DevBackNTSicredi.service.scheduled.messageobj.SessionResultMessage;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SessionScheduledTasks {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    private Gson gson = new Gson();

    @Scheduled(fixedRate = 5000)
    public void postDoneSessionResultsOnMessaging() {
        List<Session> sessions = sessionService.findAllSessions();
        for(Session session : sessions) {
            if(new Date().after(session.getEnd())){
                int yesCount = sessionService.findYesCountBySessionId(session);
                int noCount = sessionService.findNoCountBySessionId(session);
                amqpTemplate.convertAndSend("direct-exchange","admin",
                                                gson.toJson(new SessionResultMessage(session.getId(), yesCount, noCount)));
            }
        }
    }
}
