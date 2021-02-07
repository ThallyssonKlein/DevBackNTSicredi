package br.com.ntconsult.DevBackNTSicredi.service.scheduled.messageobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionResultMessage {

    private long sessionId;
    private int yesCount;
    private int noCount;
}
