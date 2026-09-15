package com.example.JavaProject.Hostelproject.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

//spring.kafka.bootstrap-servers=localhost:8092,localhost:8093
//spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
//spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JacksonJsonSerializer
//
//
//spring.kafka.producer.properties.enable.idempotence=true
//spring.kafka.producer.acks=all
//spring.kafka.producer.properties.max.in.flight.requests.per.connection=5
//spring.kafka.producer.retries=10
//spring.kafka.producer.properties.retry.backoff.ms=1000
//spring.kafka.producer.properties.delivery.timeout.ms=120000
//
//kafka-event-topic-name=image-generated-topic
@Configuration
public class KafkaConfig {
    @Autowired
    Environment environment;
 @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
 @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;
 @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;
 @Value("${spring.kafka.producer.acks}")
    private String acks;
    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimesOut;
    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private String idempotence;
    @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private String inFlightRequets;
    @Value("${spring.kafka.producer.retries}")
    private int retries;

    Map<String,Object> producerConfig(){
        Map<String,Object> kafkaConfig = new HashMap<>();
        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,keySerializer);
        kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializer);
        kafkaConfig.put(ProducerConfig.ACKS_CONFIG,acks);
        kafkaConfig.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,idempotence);
        kafkaConfig.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,deliveryTimesOut);
        kafkaConfig.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,inFlightRequets);
        kafkaConfig.put(ProducerConfig.RETRIES_CONFIG,retries);
        return kafkaConfig;
    }

    @Bean
    public ProducerFactory<String,Object> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String,Object>kafkaTemplate(ProducerFactory producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(environment.getProperty("kafka-event-topic-name"))
                .partitions(3).replicas(3)
                .configs(Map.of("min-insync.replicas","2")).build();

    }
}
