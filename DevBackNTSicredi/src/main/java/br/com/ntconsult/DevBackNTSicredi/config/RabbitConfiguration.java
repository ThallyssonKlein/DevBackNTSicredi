package br.com.ntconsult.DevBackNTSicredi.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    Queue adminQueue() {
        return new Queue("spring-boot-queue", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("spring-boot-exchenge");
    }

    @Bean
    Binding adminBinding(Queue adminQueue, DirectExchange exchange) {
        return BindingBuilder.bind(adminQueue).to(exchange).with("spring-boot");
    }

}



