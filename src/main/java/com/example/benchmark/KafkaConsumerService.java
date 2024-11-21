package com.example.benchmark;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test_topic", groupId = "test_group")
    public void consume(String message) {
    }
}
