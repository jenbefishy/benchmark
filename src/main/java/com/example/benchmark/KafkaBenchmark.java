package com.example.benchmark;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@State(Scope.Benchmark)
@Component
public class KafkaBenchmark {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    public KafkaBenchmark() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.benchmark");
        context.refresh();

        this.kafkaProducerService = context.getBean(KafkaProducerService.class);
    }



    @Benchmark
    public void testSimple() {
        kafkaProducerService.sendMessage("Hello, World!");
    }

    @Benchmark
    @Group("loadBalance")
    public void testLoadBalancingProducer1() {
        kafkaProducerService.sendMessage("Message from Producer 1");
    }

    @Benchmark
    @Group("loadBalance")
    public void testLoadBalancingProducer2() {
        kafkaProducerService.sendMessage("Message from Producer 2");
    }

    @Benchmark
    @Group("loadBalance")
    public void testLoadBalancingProducer3() {
        kafkaProducerService.sendMessage("Message from Producer 3");
    }

    @Benchmark
    @Group("multipleConsumers")
    public void testMultipleConsumersProducer() {
        kafkaProducerService.sendMessage("Message for Consumers");
    }

    @Benchmark
    @Group("multipleConsumers")
    public void testMultipleConsumersConsumer1() {
        kafkaConsumerService.consume("Message for Consumer 1");
    }

    @Benchmark
    @Group("multipleConsumers")
    public void testMultipleConsumersConsumer2() {
        kafkaConsumerService.consume("Message for Consumer 2");
    }

    @Benchmark
    @Group("multipleConsumers")
    public void testMultipleConsumersConsumer3() {
        kafkaConsumerService.consume("Message for Consumer 3");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersProducer1() {
        kafkaProducerService.sendMessage("Message from Producer 1");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersProducer2() {
        kafkaProducerService.sendMessage("Message from Producer 2");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersProducer3() {
        kafkaProducerService.sendMessage("Message from Producer 3");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersConsumer1() {
        kafkaConsumerService.consume("Message for Consumer 1");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersConsumer2() {
        kafkaConsumerService.consume("Message for Consumer 2");
    }

    @Benchmark
    @Group("loadBalanceAndMultipleConsumers")
    public void testLoadBalancingAndMultipleConsumersConsumer3() {
        kafkaConsumerService.consume("Message for Consumer 3");
    }

    @Benchmark
    public void testStressTestProducers() {
        for (int i = 0; i < 10; i++) {
            kafkaProducerService.sendMessage("Message from Producer " + i);
        }
    }

    @Benchmark
    public void testStressTestConsumers() {
        for (int i = 0; i < 10; i++) {
            kafkaConsumerService.consume("Message for Consumer " + i);
        }
    }
}