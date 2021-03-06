package br.com.ntconsult.DevBackNTSicredi.service.scheduled;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.service.SessionService;
import br.com.ntconsult.DevBackNTSicredi.service.scheduled.messageobj.SessionResultMessage;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
            if(LocalDateTime.now(ZoneId.of( "Brazil/East" )).isAfter(session.getEnd())){
                int yesCount = sessionService.findYesCountBySessionId(session);
                int noCount = sessionService.findNoCountBySessionId(session);
                amqpTemplate.convertAndSend("votingsessionmanager-exchange","votingsessionmanager",
                                                gson.toJson(new SessionResultMessage(session.getId(), yesCount, noCount)));
            }
        }
    }
}
