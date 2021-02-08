package br.com.ntconsult.DevBackNTSicredi.resource;

import br.com.ntconsult.DevBackNTSicredi.resource.responseobj.ResultResponseObj;
import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.service.SessionService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Sessões")
@CrossOrigin(origins = "*")
public class SessionResource {

    @Autowired
    private SessionService sessionService;
    private Gson gson = new Gson();

    @PostMapping("/v1/session")
    @ApiOperation(value ="Inicia uma nova sessão")
    public Session postSession(@RequestBody @Valid Session session, HttpServletResponse res) {
        Session result = sessionService.postSession(session);
        if(result == null){
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @GetMapping("/v1/session/{id}/results")
    @ApiOperation(value = "Busca os resultados de uma sessão caso finalizada, e caso não finalizada direciona para os detalhes da sessão.")
    public String getSessionResults(@PathVariable(value = "id") @Valid long id, HttpServletResponse res) throws IOException {
        Optional<Session> session = sessionService.getOneSession(id);
        if(session.isPresent()){
            if(LocalDateTime.now(ZoneId.of( "Brazil/East" )).isAfter(session.get().getEnd())){
                int yesCount = sessionService.findYesCountBySessionId(session.get());
                int noCount = sessionService.findNoCountBySessionId(session.get());
                res.setContentType("application/json");
                return gson.toJson(new ResultResponseObj(yesCount, noCount));
            }else{
                res.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                res.sendRedirect("/api/v1/session/" + String.valueOf(id));
                return null;
            }
        }else{
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("/v1/session/{id}")
    @ApiOperation(value = "Busca uma sessão pela ID")
    public Optional<Session> getOneSession(@PathVariable(value = "id") @Valid long id){
        return sessionService.getOneSession(id);
    }

}
