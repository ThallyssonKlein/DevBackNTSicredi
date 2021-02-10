package br.com.ntconsult.DevBackNTSicredi.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    Queue adminQueue() {
        return new Queue("votingsessionmanager-queue", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("votingsessionmanager-exchange");
    }

    @Bean
    Binding adminBinding(Queue adminQueue, DirectExchange exchange) {
        return BindingBuilder.bind(adminQueue).to(exchange).with("votingsessionmanager");
    }

}



