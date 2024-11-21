
package com.example.benchmark;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}
