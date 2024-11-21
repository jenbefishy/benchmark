package com.example.benchmark;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@State(Scope.Benchmark)
@Component
public class RabbitMQBenchmark {

    @Autowired
    private RabbitMQProducerService rabbitMQProducerService;

    @Autowired
    private RabbitMQConsumerService rabbitMQConsumerService;

    public RabbitMQBenchmark() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.benchmark");
        context.refresh();

        this.rabbitMQProducerService = context.getBean(RabbitMQProducerService.class);
        this.rabbitMQConsumerService = context.getBean(RabbitMQConsumerService.class);
    }

    @Benchmark
    public void testSimple() {
        rabbitMQProducerService.sendMessage("Hello, World!");
    }

    @Benchmark
    @Group("loadBalance")
    public void testLoadBalancingProducer1() {
        rabbitMQProducerService.sendMessage("Message from Producer 1");
    }

    @Benchmark
    @Group("loadBalance")
    public void testLoadBalancingProducer2() {
        rabbitMQProducerService.sendMessage("Message from Producer 2");
    }

    @Benchmark
    @Group("loadBalance")
    public void testLoadBalancingProducer3() {
        rabbitMQProducerService.sendMessage("Message from Producer 3");
    }

    @Benchmark
    @Group("multipleConsumers")
    public void testMultipleConsumersProducer() {
        rabbitMQProducerService.sendMessage("Message for Consumers");
    }

    @Benchmark
    @Group("multipleConsumers")
    public void testMultipleConsumersConsumer1() {
        rabbitMQConsumerService.receiveMessage("Message for Consumer 1");
    }

    @Benchmark
    @Group("multipleConsumers")
    public void testMultipleConsumersConsumer2() {
        rabbitMQConsumerService.receiveMessage("Message for Consumer 2");
    }

    @Benchmark
    @Group("multipleConsumers")
    public void testMultipleConsumersConsumer3() {
        rabbitMQConsumerService.receiveMessage("Message for Consumer 3");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersProducer1() {
        rabbitMQProducerService.sendMessage("Message from Producer 1");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersProducer2() {
        rabbitMQProducerService.sendMessage("Message from Producer 2");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersProducer3() {
        rabbitMQProducerService.sendMessage("Message from Producer 3");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersConsumer1() {
        rabbitMQConsumerService.receiveMessage("Message for Consumer 1");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersConsumer2() {
        rabbitMQConsumerService.receiveMessage("Message for Consumer 2");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersConsumer3() {
        rabbitMQConsumerService.receiveMessage("Message for Consumer 3");
    }

    @Benchmark
    public void testStressTestProducers() {
        for (int i = 0; i < 10; i++) {
            rabbitMQProducerService.sendMessage("Message from Producer " + i);
        }
    }

    @Benchmark
    public void testStressTestConsumers() {
        for (int i = 0; i < 10; i++) {
            rabbitMQConsumerService.receiveMessage("Message for Consumer " + i);
        }
    }
}

