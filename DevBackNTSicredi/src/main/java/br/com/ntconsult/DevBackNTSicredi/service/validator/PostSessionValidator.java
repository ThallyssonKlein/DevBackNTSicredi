package br.com.ntconsult.DevBackNTSicredi.service.validator;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class PostSessionValidator{
    public boolean validateIfSessionEndDateIsBeforeThanNow(Session session){
        return session.getEnd().isBefore(LocalDateTime.now(ZoneId.of( "Brazil/East" )));
    }
}