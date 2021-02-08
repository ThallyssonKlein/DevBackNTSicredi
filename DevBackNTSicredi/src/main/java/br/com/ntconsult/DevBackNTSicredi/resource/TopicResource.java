package br.com.ntconsult.DevBackNTSicredi.resource;

import br.com.ntconsult.DevBackNTSicredi.entity.Topic;
import br.com.ntconsult.DevBackNTSicredi.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Pautas")
@CrossOrigin(origins = "*")
public class TopicResource {
    @Autowired
    private TopicService topicService;

    @PostMapping("/v1/topic")
    @ApiOperation(value = "Cria uma nova pauta")
    public Topic postTopic(@Valid @RequestBody Topic topic){
        System.out.println("No topic");
        System.out.println(new Date());
        return topicService.postTopic(topic);
    }
}
