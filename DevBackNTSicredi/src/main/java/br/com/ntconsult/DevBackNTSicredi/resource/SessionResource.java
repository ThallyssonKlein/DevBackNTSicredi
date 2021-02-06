package br.com.ntconsult.DevBackNTSicredi.resource;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Sessões")
@CrossOrigin(origins = "*")
public class SessionResource {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/session")
    @ApiOperation(value ="Inicia uma nova sessão")
    public Session postSession(@RequestBody Session session) {
        return sessionService.postSession(session);
    }
}
