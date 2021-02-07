package br.com.ntconsult.DevBackNTSicredi.resource;

import br.com.ntconsult.DevBackNTSicredi.entity.Result;
import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.service.SessionService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Sessões")
@CrossOrigin(origins = "*")
public class SessionResource {

    @Autowired
    private SessionService sessionService;
    private Gson gson = new Gson();

    @PostMapping("/session")
    @ApiOperation(value ="Inicia uma nova sessão")
    public Session postSession(@RequestBody Session session) {
        return sessionService.postSession(session);
    }

    @GetMapping("/session/{id}/results")
    @ApiOperation(value = "Busca os resultados de uma sessão caso aberta e existente")
    public String getSessionResults(@PathVariable(value = "id") @Valid long id, HttpServletResponse res) throws IOException {
        Optional<Session> session = sessionService.getOneSession(id);
        if(session.isPresent()){
            if(new Date().after(session.get().getEnd())){
                int yesCount = sessionService.findYesCountBySessionId(session.get());
                int noCount = sessionService.findNoCountBySessionId(session.get());
                res.setContentType("application/json");
                return gson.toJson(new Result(yesCount, noCount));
            }else{
                res.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                res.sendRedirect("/api/session/" + String.valueOf(id));
                return null;
            }
        }else{
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("/session/{id}")
    @ApiOperation(value = "Busca uma sessão pela ID")
    public Optional<Session> getOneSession(@PathVariable(value = "id") @Valid long id){
        return sessionService.getOneSession(id);
    }

}
