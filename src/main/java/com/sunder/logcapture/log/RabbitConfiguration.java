package com.sunder.logcapture.log;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {


    @Autowired
    private ConnectionFactory connectionFactory;


    @Bean
    Queue queue() {
        return new Queue("sunder.log.queue", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("sunder.log.exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("sunder.log.#");
    }


    @Bean(name = "indexTemplate")
    public RabbitTemplate indexTemplate() {

        return createRabbitTemplate("sunder.log.exchange", "sunder.log.queue");
    }


    private RabbitTemplate createRabbitTemplate(final String exchange, final String queue) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setRoutingKey(queue);
        return rabbitTemplate;

    }

}
