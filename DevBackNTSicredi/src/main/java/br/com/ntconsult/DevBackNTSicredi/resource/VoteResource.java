package br.com.ntconsult.DevBackNTSicredi.resource;

import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import br.com.ntconsult.DevBackNTSicredi.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Votos")
@CrossOrigin(origins = "*")
public class VoteResource {

    @Autowired
    private VoteService voteService;

    @PostMapping("/v1/vote")
    @ApiOperation(value = "Cria um novo voto")
    public Vote postVote(@Valid @RequestBody Vote vote, HttpServletResponse res){
        Vote result = voteService.postVote(vote);
        if(result == null){
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }

    @PostMapping("/v2/vote")
    @ApiOperation(value = "Cria um novo voto com a validação do id do associado")
    public Vote postVoteWithAssociateIdValidation(@Valid @RequestBody Vote vote, HttpServletResponse res){
        Vote result = voteService.postVoteWithAssociateIdValidation(vote);
        if(result == null){
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return result;
    }
}
